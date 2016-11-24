package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.facade.StudentFacade;
import com.fi.ls.rest.ApiEndpoints;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_STUDENTS)
public class StudentsController {

	final static Logger logger = LoggerFactory.getLogger(StudentsController.class);

	@Inject
	private StudentFacade studentFacade;

	/**
	 * get all the students
	 * 
	 * @return list of StudentDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<StudentDTO> getStudents() {

		logger.debug("rest getStudents()");
		return studentFacade.getAllStudents();
	}
}
