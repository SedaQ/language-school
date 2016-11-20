package com.fi.ls.rest.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fi.ls.rest.ApiEndpoints;

/**
 * @author Pavel Šeda (441048)
 *
 */
@RestController
public class MainController {

	/**
	 * The main entry point of the REST API Provides access to all the resources
	 * with links to resource URIs Can be even extended further so that all the
	 * actions on all the resources are available and can be reused in all the
	 * controllers (possibly in full HATEOAS style)
	 * 
	 * @return resources uris
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final Map<String, String> getResources() {

		Map<String, String> resourcesMap = new HashMap<>();

		resourcesMap.put("users_uri", ApiEndpoints.ROOT_URI_USERS);
		resourcesMap.put("students_uri", ApiEndpoints.ROOT_URI_STUDENTS);
		resourcesMap.put("lecturers_uri", ApiEndpoints.ROOT_URI_LECTURERS);
		resourcesMap.put("courses_uri", ApiEndpoints.ROOT_URI_COURSES);
		resourcesMap.put("lectures_uri", ApiEndpoints.ROOT_URI_LECTURES);
		resourcesMap.put("languages_uri", ApiEndpoints.ROOT_URI_LANGUAGES);

		return Collections.unmodifiableMap(resourcesMap);
	}

}
