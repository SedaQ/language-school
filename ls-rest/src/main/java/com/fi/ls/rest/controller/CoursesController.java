package com.fi.ls.rest.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.rest.ApiEndpoints;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_COURSES)
public class CoursesController {

	final static Logger logger = LoggerFactory.getLogger(CoursesController.class);

	@Inject
	private CourseFacade courseFacade;

	/**
	 * get all the courses
	 * 
	 * @return list of CoursesDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<CourseDTO> getCourses() {

		logger.debug("rest getCourses()");
		return courseFacade.getAllCourses();
	}

}
