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

import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.facade.LecturerFacade;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	private final static Logger logger = LoggerFactory.getLogger(LecturerController.class);

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
	

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newLecturer(Model model) {
		logger.debug("new");
		model.addAttribute("lecturerCreate", new LecturerCreateDTO());
		return "lecturer/lecturerNew";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createLecturer(@Valid @ModelAttribute("lecturerCreate") LecturerCreateDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		Optional<LecturerDTO> cdto = lecturerFacade.createLecturer(formBean);
		return "redirect:" + uriBuilder.path("/lecturer/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("lecturer") LecturerDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		Optional<LecturerDTO> cdto = lecturerFacade.updateLecturer(formBean);
		return "redirect:" + uriBuilder.path("/lecturer/list").buildAndExpand().encode().toUriString();
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
