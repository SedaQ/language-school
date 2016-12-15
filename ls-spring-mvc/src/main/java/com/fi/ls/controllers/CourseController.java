package com.fi.ls.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.facade.LectureFacade;
import java.security.SecureRandom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {

	private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Inject
	private CourseFacade courseFacade;
        
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("courses", courseFacade.getAllCourses());
		return "course/courseList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		logger.debug("view: ", id);

		CourseDTO course = courseFacade.getCourseById(id).get();

		model.addAttribute("course", course);
		model.addAttribute("lecturesInCourse", courseFacade.getCourseById(id).get().getListOfLectures());
		return "course/courseView";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newCourse(Model model) {
		logger.debug("new");
		model.addAttribute("courseCreate", new CourseCreateDTO());
		model.addAttribute("proficiencylevels", new ArrayList<>(Arrays.asList(ProficiencyLevel.values())));
		return "course/courseNew";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createCourse(@Valid @ModelAttribute("courseCreate") CourseCreateDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("create");
		// formBean.setProficiencyLevel(ProficiencyLevel.C1);
		Optional<CourseDTO> cdto = courseFacade.create(formBean);
//                return "course/courseList";
		return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCourse(@PathVariable Long id, Model model) {
		model.addAttribute("course", courseFacade.getCourseById(id).get());
		model.addAttribute("proficiencylevels", new ArrayList<>(Arrays.asList(ProficiencyLevel.values())));
		logger.debug("edit");
		return "course/courseEdit";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("course") CourseDTO formBean,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {
		logger.debug("update");
		Optional<CourseDTO> toUpdate = courseFacade.getCourseById(id);
		Optional<CourseDTO> cdto = courseFacade.updateCourse(id);
		return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("delete");
		courseFacade.deleteCourse(id);
		return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
	}

}
