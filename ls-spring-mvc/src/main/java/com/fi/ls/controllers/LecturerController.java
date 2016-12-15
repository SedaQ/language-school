package com.fi.ls.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.facade.LecturerFacade;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Inject
	private LecturerFacade lecturerFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("lecturers", lecturerFacade.getAllLecturers());
		return "lecturer/lecturerList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("lecturer", lecturerFacade.getLecturerById(id).get());
		return "lecturer/lecturerView";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) {
		model.addAttribute("lecturer", lecturerFacade.getLecturerById(id).get());
		logger.debug("edit");
		return "lecturer/lecturerEdit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("delete");
		lecturerFacade.deleteLecturer(lecturerFacade.getLecturerById(id).get());
		return "redirect:" + uriBuilder.path("/lecturer/list").buildAndExpand().encode().toUriString();
	}
}
