package com.fi.ls.controllers;

import com.fi.ls.dto.course.CourseDTO;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.entity.Lecture;
import com.fi.ls.enums.UserRoles;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.facade.LSUserFacade;
import com.fi.ls.facade.LectureFacade;
import com.fi.ls.facade.LecturerFacade;
import com.fi.ls.facade.StudentFacade;
import com.fi.ls.helpers.Helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lecture")
public class LectureController {

	private final static Logger logger = LoggerFactory.getLogger(LectureController.class);

	@Inject
	private LectureFacade lectureFacade;

	@Inject
	private CourseFacade courseFacade;

	@Inject
	private LecturerFacade lecturerFacade;

	@Inject
	private StudentFacade studentFacade;

	@Inject
	private LSUserFacade userFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		if (Helpers.hasRole(UserRoles.ROLE_STUDENT.name())) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Long studentId = userFacade.getUserByEmail(email).get().getId();
			Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);
			model.addAttribute("studentEnrolledLectures", studentDTO.get().getListOfLectures());
		}
		List<LectureDTO> lectures = lectureFacade.getAllLectures();
		Collections.sort(lectures, (o1, o2) -> o1.getDayTime().compareTo(o2.getDayTime()));
		model.addAttribute("lectures", lectures);
		return "lecture/lectureList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		if (Helpers.hasRole(UserRoles.ROLE_STUDENT.name())) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Long studentId = userFacade.getUserByEmail(email).get().getId();
			Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);
			model.addAttribute("studentEnrolledLectures", studentDTO.get().getListOfLectures());
		}
		LectureDTO lecture = lectureFacade.getLectureById(id).get();
		model.addAttribute("lecture", lecture);
		model.addAttribute("lectureInCourse", lecture.getListOfCourses());
		model.addAttribute("courseLecturers", lecture.getListOfLecturers());
		model.addAttribute("courseStudents", lecture.getListOfStudents());
		return "lecture/lectureView";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newLecture(Model model) {
		logger.debug("new");
		List<String> courses = new ArrayList<String>();
		courses.add("No course");
		for (CourseDTO courseDTO : courseFacade.getAllCourses()) {
			courses.add(courseDTO.getName());
		}
		model.addAttribute("selectedCourse", "No course");
		model.addAttribute("courses", courses);
		model.addAttribute("lectureCreate", new LectureCreateDTO());
		model.addAttribute("courseId", null);
		model.addAttribute("lecturerId", null);
		return "lecture/lectureNew";
	}

	@RequestMapping(value = "/new/lecturer={lecturerId}", method = RequestMethod.GET)
	public String newLectureWithLecturer(@PathVariable long lecturerId, Model model) {
		logger.debug("new/lecturer={lecturerId}", lecturerId);
		List<String> courses = new ArrayList<String>();
		courses.add("No course");
		for (CourseDTO courseDTO : courseFacade.getAllCourses()) {
			courses.add(courseDTO.getName());
		}
		model.addAttribute("selectedCourse", "No course");
		model.addAttribute("courses", courses);
		model.addAttribute("lectureCreate", new LectureCreateDTO());
		model.addAttribute("courseId", null);
		model.addAttribute("lecturerId", lecturerId);
		return "lecture/lectureNew";
	}

	@RequestMapping(value = "/newLectureInCourse/{id}", method = RequestMethod.GET)
	public String newLectureToCourse(@PathVariable Long id, Model model) {
		logger.debug("newLectureToCourse");
		List<String> courses = new ArrayList<String>();
		courses.add("No course");
		for (CourseDTO courseDTO : courseFacade.getAllCourses()) {

			courses.add(courseDTO.getName());

		}
		model.addAttribute("selectedCourse", courseFacade.getCourseById(id).get().getName());
		model.addAttribute("courses", courses);
		model.addAttribute("lectureCreate", new LectureCreateDTO());
		model.addAttribute("courseId", id);
		model.addAttribute("lecturerId", null);
		return "lecture/lectureNew";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createLecture(@Valid @ModelAttribute("lectureCreate") LectureCreateDTO lectureCreateBean,
			BindingResult bindingResult, @RequestParam(value = "courseId") Long courseId,
			@RequestParam(value = "lecturerId") Long lecturerId, @RequestParam(value = "courseName") String courseName,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		if (bindingResult.hasErrors()) {
			List<String> courses = new ArrayList<String>();
			courses.add("No course");
			for (CourseDTO courseDTO : courseFacade.getAllCourses()) {
				courses.add(courseDTO.getName());
			}
			model.addAttribute("selectedCourse", courseName);
			model.addAttribute("courses", courses);
			//model.addAttribute("lectureCreate", new LectureCreateDTO());
			//model.addAttribute("courseId", null);
			//model.addAttribute("lecturerId", null);
			return "lecture/lectureNew";
		}
		Optional<LectureDTO> cdto = lectureFacade.createLecture(lectureCreateBean);
		if (courseName != null & !"No course".equals(courseName)) {
			courseFacade.addLecture(courseFacade.getCourseByName(courseName).get(), cdto.get());
		}
		if (courseId != null && lecturerId == null) {
			return "redirect:" + uriBuilder.path("/course/view/{id}").buildAndExpand(courseId).encode().toUriString();
		}
		if (courseId == null && lecturerId != null) {
			lecturerFacade.addLecture(lecturerFacade.getLecturerById(lecturerId).get(), cdto.get());
			return "redirect:"
					+ uriBuilder.path("/lecturer/view/{id}").buildAndExpand(lecturerId).encode().toUriString();
		}
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateLecture(@PathVariable Long id, @Valid @ModelAttribute("lecture") LectureDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		if (bindingResult.hasErrors()) {

			return "lecture/lectureEdit";

		}
		Optional<LectureDTO> cdto = lectureFacade.updateLecture(formBean);
		return "redirect:" + uriBuilder.path("/lecture/view/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editLecture(@PathVariable Long id, Model model) {
                LectureDTO lecture = lectureFacade.getLectureById(id).get();
		model.addAttribute("lecture", lecture);
                model.addAttribute("dateTime", lecture.getDayTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		logger.debug("edit");
		return "lecture/lectureEdit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteLecture(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {

		logger.debug("delete");

		for (CourseDTO courseDTO : lectureFacade.getLectureById(id).get().getListOfCourses()) {

			List<LectureDTO> lectureList;
			lectureList = new ArrayList<>();
			for (LectureDTO l : courseDTO.getListOfLectures()) {
				lectureList.add(l);
			}
			lectureList.remove(lectureFacade.getLectureById(id).get());
			courseDTO.setListOfLectures(lectureList);
			courseFacade.updateCourse(courseDTO);

		}

		for (LecturerDTO lecturerDTO : lectureFacade.getLectureById(id).get().getListOfLecturers()) {

			List<LectureDTO> lectureList;
			lectureList = new ArrayList<>();
			for (LectureDTO l : lecturerDTO.getListOfLectures()) {
				lectureList.add(l);
			}
			lectureList.remove(lectureFacade.getLectureById(id).get());
			lecturerDTO.setListOfLectures(lectureList);
			lecturerFacade.updateLecturer(lecturerDTO);

		}

		for (StudentDTO studentDTO : lectureFacade.getLectureById(id).get().getListOfStudents()) {

			List<LectureDTO> lectureList;
			lectureList = new ArrayList<>();
			for (LectureDTO l : studentDTO.getListOfLectures()) {
				lectureList.add(l);
			}
			lectureList.remove(lectureFacade.getLectureById(id).get());
			studentDTO.setListOfLectures(lectureList);
			studentFacade.updateStudent(studentDTO);

		}

		lectureFacade.deleteLecture(id);
		return "redirect:" + uriBuilder.path("/lecture/list").toUriString();
	}
}
