package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.facade.LectureFacade;
import com.fi.ls.rest.ApiEndpoints;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LECTURES)
public class LecturesController {

	final static Logger logger = LoggerFactory.getLogger(LecturesController.class);

	@Inject
	private LectureFacade lectureFacade;

	/**
	 * get all the lectures
	 * 
	 * @return list of LectureDTO
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<LectureDTO> getLectures() {

		logger.debug("rest getLectures()");
		return lectureFacade.getAllLectures();
	}
}
