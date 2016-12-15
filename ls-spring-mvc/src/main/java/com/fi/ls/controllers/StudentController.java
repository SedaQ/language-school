package com.fi.ls.controllers;

import java.util.Optional;

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
import com.fi.ls.facade.StudentFacade;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Inject
	private StudentFacade studentFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("students", studentFacade.getAllStudents());
		return "student/studentList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("student", studentFacade.getStudentById(id).get());
		return "student/studentView";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newStudent(Model model) {
		logger.debug("new");
		model.addAttribute("studentCreate", new StudentCreateDTO());
		return "student/studentNew";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createStudent(@Valid @ModelAttribute("studentCreate") StudentCreateDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		Optional<StudentDTO> cdto = studentFacade.createStudent(formBean);
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

}
