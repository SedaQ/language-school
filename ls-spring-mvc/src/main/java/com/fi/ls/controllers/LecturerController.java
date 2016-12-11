package com.fi.ls.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fi.ls.facade.LecturerFacade;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Inject
	private LecturerFacade lecturerFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("lecturers", lecturerFacade.getAllLecturers());
		return "lecturer/lecturersList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("lecturer", lecturerFacade.getLecturerById(id).get());
		return "lecturer/lecturerView";
	}
}
