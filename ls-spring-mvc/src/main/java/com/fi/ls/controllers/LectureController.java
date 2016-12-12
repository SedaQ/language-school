package com.fi.ls.controllers;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.fi.ls.facade.LectureFacade;

@Controller
@RequestMapping("/lecture")
public class LectureController {

	private final static Logger logger = LoggerFactory.getLogger(LectureController.class);

	@Inject
	private LectureFacade lectureFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("lectures", lectureFacade.getAllLectures());
		return "lecture/lecturesList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		model.addAttribute("lecture", lectureFacade.getLectureById(id).get());
		return "lecture/lectureView";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editLecture(@PathVariable Long id, Model model) {
		model.addAttribute("lecture", lectureFacade.getLectureById(id).get());
		logger.debug("edit");
		return "lecture/lectureEdit";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteLecture(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("delete");
		lectureFacade.deleteLecture(id);
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	}
}
