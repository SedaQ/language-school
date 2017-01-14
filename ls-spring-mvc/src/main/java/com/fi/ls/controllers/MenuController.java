package com.fi.ls.controllers;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fi.ls.enums.UserRoles;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.facade.LSUserFacade;
import com.fi.ls.facade.LectureFacade;
import com.fi.ls.facade.LecturerFacade;
import com.fi.ls.facade.StudentFacade;
import com.fi.ls.helpers.Helpers;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Controller
public class MenuController {

	private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Inject
	private StudentFacade studentFacade;

	@Inject
	private LecturerFacade lecturerFacade;

	@Inject
	private CourseFacade courseFacade;

	@Inject
	private LSUserFacade lsUserFacade;

	@RequestMapping(value = "/home")
	public String index() {
		return "home";
	}

	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}

	@RequestMapping(value = "/about")
	public String about() {
		return "about";
	}

	@RequestMapping(value = "/userDetail")
	public String userDetail(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Long userId = lsUserFacade.getUserByEmail(email).get().getId();
		if (Helpers.hasRole(UserRoles.ROLE_STUDENT.name())) {
			model.addAttribute("student", studentFacade.getStudentById(userId).get());
			return "student/studentView";
		} else if (Helpers.hasRole(UserRoles.ROLE_LECTURER.name())) {
			model.addAttribute("lecturer", lecturerFacade.getLecturerById(userId).get());
			model.addAttribute("lecturerLanguages",
					lecturerFacade.findAllLecturerLanguages(lecturerFacade.getLecturerById(userId).get()));
			model.addAttribute("lecturerLectures", lecturerFacade.getLecturerById(userId).get().getListOfLectures());
			return "lecturer/lecturerView";
		} else if (Helpers.hasRole(UserRoles.ROLE_ADMIN.name())) {
			model.addAttribute("admin", lsUserFacade.getUserByEmail(email).get());
			return "admin/adminView";
		} else {
			return "";
		}
	}
}
