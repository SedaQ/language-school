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

import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
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

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createLecture(@Valid @ModelAttribute("lectureCreate") LectureCreateDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		Optional<LectureDTO> cdto = lectureFacade.createLecture(formBean);
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateLecture(@PathVariable Long id, @Valid @ModelAttribute("lecture") LectureDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		Optional<LectureDTO> cdto = lectureFacade.updateLecture(formBean);
		return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
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
		logger.debug("delete");
		lectureFacade.deleteLecture(id);
		return "redirect:" + uriBuilder.path("/course/view/{" + myID.toString() + "}").buildAndExpand().encode().toUriString();
	}
}
