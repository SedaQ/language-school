package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.facade.LanguageFacade;
import com.fi.ls.rest.ApiEndpoints;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LANGUAGES)
public class LanguagesController {

	final static Logger logger = LoggerFactory.getLogger(LanguagesController.class);

	@Inject
	private LanguageFacade languageFacade;

	/**
	 * get all the languages
	 * 
	 * @return list of LanguageDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final List<LanguageDTO> getLanguages() {

		logger.debug("rest getLanguages()");
		return languageFacade.getAllLanguages();
	}
}
