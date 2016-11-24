package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.facade.LSUserFacade;
import com.fi.ls.rest.ApiEndpoints;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_USERS)
public class UsersController {

	final static Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Inject
	private LSUserFacade userFacade;

	/**
	 * get all the users
	 * 
	 * @return list of LSUserDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<LSUserDTO> getLSUsers() {

		logger.debug("rest getLSUsers()");
		return userFacade.getAllUsers();
	}

}
