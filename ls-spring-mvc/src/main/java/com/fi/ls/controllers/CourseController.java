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
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.facade.CourseFacade;
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

	@PostConstruct
	public void initCourses() {
		CourseCreateDTO course1 = new CourseCreateDTO();
		course1.setName("course1");
		course1.setLanguage("English");
		course1.setProficiencyLevel(ProficiencyLevel.A1);

		CourseCreateDTO course2 = new CourseCreateDTO();
		course2.setName("course2");
		course2.setLanguage("Czech");
		course2.setProficiencyLevel(ProficiencyLevel.A1);

		CourseCreateDTO course3 = new CourseCreateDTO();
		course3.setName("course3");
		course3.setLanguage("English");
		course3.setProficiencyLevel(ProficiencyLevel.B2);

		CourseCreateDTO course4 = new CourseCreateDTO();
		course4.setName("course4");
		course4.setLanguage("Czech");
		course4.setProficiencyLevel(ProficiencyLevel.C1);

		CourseCreateDTO course5 = new CourseCreateDTO();
		course5.setName("course5");
		course5.setLanguage("French");
		course5.setProficiencyLevel(ProficiencyLevel.B1);

		courseFacade.create(course1);
		courseFacade.create(course2);
		courseFacade.create(course3);
		courseFacade.create(course4);
		courseFacade.create(course5);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("courses", courseFacade.getAllCourses());
		return "course/coursesList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, Model model) {
		logger.debug("view: ", id);
                CourseDTO kurz = courseFacade.getCourseById(id).get();
		model.addAttribute("course", kurz);
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
        public String createCourse(@Valid @ModelAttribute("courseCreate") CourseCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
            logger.debug("create");
            //formBean.setProficiencyLevel(ProficiencyLevel.C1);
            Optional<CourseDTO> cdto = courseFacade.create(formBean);
            return "redirect:"+uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
        }
       

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCourse(@PathVariable Long id, Model model) {
		model.addAttribute("course", courseFacade.getCourseById(id).get());
                model.addAttribute("proficiencylevels", new ArrayList<>(Arrays.asList(ProficiencyLevel.values())));
                logger.debug("edit");
		return "course/courseEdit";
	}
        
        @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
        public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("course") CourseDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
            logger.debug("update");
            Optional<CourseDTO> toUpdate = courseFacade.getCourseById(id);
            Optional<CourseDTO> cdto = courseFacade.updateCourse(id);
            return "redirect:"+uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
        }
        
        @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
        public String deleteCourse(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
            logger.debug("delete");
            courseFacade.deleteCourse(id);
            return "redirect:"+uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
        }

}
