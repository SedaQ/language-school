package com.fi.ls.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fi.ls.facade.LectureFacade;

@Controller
@RequestMapping("/lecture")
public class LectureController {

	@Inject
	private LectureFacade lectureFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("lectures", lectureFacade.getAllLectures());
		return "lecture/lecturesList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("lecture", lectureFacade.getLectureById(id).get());
		return "lecture/lectureView";
	}
}
