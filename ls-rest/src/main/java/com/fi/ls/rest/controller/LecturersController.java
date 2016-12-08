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
import com.fi.ls.rest.assembler.LecturerResourceAssembler;
import com.fi.ls.rest.exception.ResourceNotFoundException;
import com.fi.ls.rest.exception.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LECTURERS)
public class LecturersController {

	final static Logger logger = LoggerFactory.getLogger(LecturersController.class);

	@Inject
	private LecturerFacade lecturerFacade;
        
        @Inject
        private LecturerResourceAssembler lecturerResourceAssembler;
        
	/**
	 * get all the lecturers (with HTTP caching)
         * curl -i -X GET http://localhost:8080/ls-rest/lecturers/
	 * 
         * @param webRequest
	 * @return list of LecturerDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LecturerDTO>>> getLanguages(WebRequest webRequest) {

            logger.debug("rest getLecturers()");
                
            Collection<LecturerDTO> lecturersDTO = lecturerFacade.getAllLecturers();
            Collection<Resource<LecturerDTO>> languageResourceCollection = new ArrayList();

            for (LecturerDTO l : lecturersDTO) {
                languageResourceCollection.add(lecturerResourceAssembler.toResource(l));
            }

            Resources<Resource<LecturerDTO>> languagesResources = new Resources<>(languageResourceCollection);
            languagesResources.add(linkTo(LecturerResourceAssembler.class).withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(languagesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(languagesResources);
	}
        
        /**
         * get lecturer by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/ls-rest/lecturers/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<LecturerDTO>> getLanguage(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getLecturer({})", id);

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.getLecturerById(id);
            if(!lecturerDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<LecturerDTO> resource = lecturerResourceAssembler.toResource(lecturerDTO.get());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturerDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }
}
