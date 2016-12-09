package com.fi.ls.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fi.ls.facade.StudentFacade;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	@Inject
	private StudentFacade studentFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("students", studentFacade.getAllStudents());
		return "student/studentsList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentFacade.getStudentById(id));
		return "student/studentView";
	}

}
