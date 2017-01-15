package com.fi.ls.controllers;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.enums.UserRoles;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.facade.LSUserFacade;
import com.fi.ls.facade.StudentFacade;
import com.fi.ls.helpers.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        
    @Inject
	private LSUserFacade userFacade;
        
    @Inject
	private StudentFacade studentFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
        List<CourseDTO> courses = courseFacade.getAllCourses();
        Collections.sort(courses, (o1, o2) -> o1.getId().compareTo(o2.getId()));
		model.addAttribute("courses", courses);
        if (Helpers.hasRole(UserRoles.ROLE_STUDENT.name())) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Long studentId = userFacade.getUserByEmail(email).get().getId();
			Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);
			model.addAttribute("studentEnrolledLectures", studentDTO.get().getListOfLectures());
		}
		return "course/courseList";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		logger.debug("view: ", id);

		CourseDTO course = courseFacade.getCourseById(id).get();
        model.addAttribute("course", course);
        if (Helpers.hasRole(UserRoles.ROLE_STUDENT.name())) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Long studentId = userFacade.getUserByEmail(email).get().getId();
			Optional<StudentDTO> studentDTO = studentFacade.getStudentById(studentId);
			model.addAttribute("studentEnrolledLectures", studentDTO.get().getListOfLectures());
		}
		List<LectureDTO> lecturesInCourse = course.getListOfLectures();
		Collections.sort(lecturesInCourse, (o1, o2) -> o1.getDayTime().compareTo(o2.getDayTime()));
		model.addAttribute("courseLectures", lecturesInCourse);
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
        if (bindingResult.hasErrors()) {
            
            model.addAttribute("proficiencylevels", new ArrayList<>(Arrays.asList(ProficiencyLevel.values())));
            return "course/courseNew";
            
        }
		// formBean.setProficiencyLevel(ProficiencyLevel.C1);
		Optional<CourseDTO> cdto = courseFacade.create(formBean);
		// return "course/courseList";
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
                if (bindingResult.hasErrors()) {
                    
                    model.addAttribute("proficiencylevels", new ArrayList<>(Arrays.asList(ProficiencyLevel.values())));
                    return "course/courseEdit";
                    
                }
		// Optional<CourseDTO> toUpdate = courseFacade.getCourseById(id);
		logger.debug("ID: " + formBean.getId().toString() + " name: " + formBean.getName() + " language: "
				+ formBean.getLanguage());
		Optional<CourseDTO> cdto = courseFacade.updateCourse(formBean);
		return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
		logger.debug("delete");
		courseFacade.deleteCourse(id);
		return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
	}

}
