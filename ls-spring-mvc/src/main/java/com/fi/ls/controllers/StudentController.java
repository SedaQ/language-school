package com.fi.ls.controllers;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.fi.ls.dto.student.StudentCreateDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.facade.LSUserFacade;
import com.fi.ls.facade.LectureFacade;
import com.fi.ls.facade.StudentFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Inject
	private StudentFacade studentFacade;

	@Inject
	private LectureFacade lectureFacade;

	@Inject
	private CourseFacade courseFacade;

	@Inject
	private LSUserFacade lsUserFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("students", studentFacade.getAllStudents());
		return "student/studentList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		StudentDTO student = studentFacade.getStudentById(id).get();
		model.addAttribute("student", student);
		// again should be Set on persistence level
		Set<LectureDTO> studentLectures = new HashSet<>(student.getListOfLectures()).stream()
				.sorted((d1, d2) -> d1.getDayTime().compareTo(d2.getDayTime())).collect(Collectors.toSet());
		model.addAttribute("studentLectures", studentLectures);
		return "student/studentView";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newStudent(Model model) {
		logger.debug("new");
		model.addAttribute("studentCreate", new StudentDTO());
		return "student/studentNew";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createStudent(@Valid @ModelAttribute("studentCreate") StudentDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		studentFacade.registerUser(formBean, formBean.getPasswordHash());
		return "redirect:" + uriBuilder.path("/student/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") StudentDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		Optional<StudentDTO> cdto = studentFacade.updateStudent(formBean);
		return "redirect:" + uriBuilder.path("/student/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentFacade.getStudentById(id).get());
		logger.debug("edit");
		return "student/studentEdit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("delete");
		studentFacade.deleteStudent(studentFacade.getStudentById(id).get());
		return "redirect:" + uriBuilder.path("/student/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/enrollToLecture/{id}", method = RequestMethod.GET)
	public String enrollToLecture(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("enrollToLecture");

		Optional<LectureDTO> lectureDTO = lectureFacade.getLectureById(id);
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Long studentId = lsUserFacade.getUserByEmail(email).get().getId();
		Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);

		studentFacade.enrollLecture(lectureDTO.get(), studentDTO.get());
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/unenrollLecture/{id}", method = RequestMethod.GET)
	public String unenrollLecture(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("unenrollLecture");

		Optional<LectureDTO> lectureDTO = lectureFacade.getLectureById(id);
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Long studentId = lsUserFacade.getUserByEmail(email).get().getId();
		Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);

		studentFacade.cancelLectureFromStudentsList(lectureDTO.get(), studentDTO.get());
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/enrollToCourse/{id}", method = RequestMethod.GET)
	public String enrollToCourse(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("enrollToCourse");

		Optional<CourseDTO> courseDTO = courseFacade.getCourseById(id);
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Long studentId = lsUserFacade.getUserByEmail(email).get().getId();
		Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);

		studentFacade.enrollCourse(courseDTO.get(), studentDTO.get());

		return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
	}

}
