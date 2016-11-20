package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.facade.LecturerFacade;
import com.fi.ls.rest.ApiEndpoints;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LECTURERS)
public class LecturersController {

	final static Logger logger = LoggerFactory.getLogger(LecturersController.class);

	@Inject
	private LecturerFacade lecturerFacade;

	/**
	 * get all the lecturers
	 * 
	 * @return list of LecturerDTO
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<LecturerDTO> getLecturers() {

		logger.debug("rest getLecturers()");
		return lecturerFacade.getAllLecturers();
	}
}
