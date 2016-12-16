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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		if (hasRole(UserRoles.ROLE_STUDENT.name())) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Long studentId = userFacade.getUserByEmail(email).get().getId();
			Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);
			model.addAttribute("studentEnrolledLectures", studentDTO.get().getListOfLectures());
		}
		model.addAttribute("lectures", lectureFacade.getAllLectures());
		return "lecture/lectureList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("lecture", lectureFacade.getLectureById(id).get());
		return "lecture/lectureView";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newLecture(Model model) {
		logger.debug("new");
		model.addAttribute("lectureCreate", new LectureCreateDTO());
		return "lecture/lectureNew";
	}

	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	// public String createLecture(@Valid @ModelAttribute("lectureCreate")
	// LectureCreateDTO formBean,
	// BindingResult bindingResult, Model model, RedirectAttributes
	// redirectAttributes,
	// UriComponentsBuilder uriBuilder) {
	// logger.debug("create");
	// System.out.println("Class room id je: " + formBean.getClassroomId());
	// System.out.println("Lecture datetime je: " + formBean.getDayTime());
	// System.out.println("Lecture topic je: " + formBean.getTopic());
	// Optional<LectureDTO> cdto = lectureFacade.createLecture(formBean);
	// return "redirect:" +
	// uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	// }

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createLecture(@RequestParam(value = "dayTime") String dayTime,
			@RequestParam(value = "classroomId") String classroomId, @RequestParam(value = "topic") String topic,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		LocalDateTime localDateTime = LocalDateTime.parse(dayTime, formatter);

		LectureCreateDTO lecture = new LectureCreateDTO();

		lecture.setDayTime(localDateTime);
		lecture.setClassroomId(classroomId);
		lecture.setTopic(topic);
		System.out.println("Class room id je: " + lecture.getClassroomId());
		System.out.println("Lecture datetime je: " + lecture.getDayTime());
		System.out.println("Lecture topic je: " + lecture.getTopic());

		Optional<LectureDTO> cdto = lectureFacade.createLecture(lecture);
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateLecture(@PathVariable Long id, @Valid @ModelAttribute("lecture") LectureDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		Optional<LectureDTO> cdto = lectureFacade.updateLecture(formBean);
		return "redirect:" + uriBuilder.path("/lecture/view/{id}").buildAndExpand(id).encode().toUriString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editLecture(@PathVariable Long id, Model model) {
		model.addAttribute("lecture", lectureFacade.getLectureById(id).get());
		logger.debug("edit");
		return "lecture/lectureEdit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteLecture(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		Long myID = lectureFacade.getLectureById(id).get().getListOfCourses().get(0).getId();

		for (CourseDTO courseDTO : lectureFacade.getLectureById(id).get().getListOfCourses()) {

			List<LectureDTO> lectureList;
			lectureList = new ArrayList<>();
			for (LectureDTO l : courseDTO.getListOfLectures()) {
				lectureList.add(l);
			}
			// lectureList = courseDTO.getListOfLectures();
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
			// lectureList = lecturerDTO.getListOfLectures();
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
			// lectureList = studentDTO.getListOfLectures();
			lectureList.remove(lectureFacade.getLectureById(id).get());
			studentDTO.setListOfLectures(lectureList);
			studentFacade.updateStudent(studentDTO);

		}

		logger.debug("delete");

		lectureFacade.deleteLecture(id);
		return "redirect:" + uriBuilder.path("/course/view/{id}").buildAndExpand(myID).encode().toUriString();
	}

	private boolean hasRole(String role) {
		// get security context from thread local
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (role.equals(auth.getAuthority()))
				return true;
		}

		return false;
	}

}
