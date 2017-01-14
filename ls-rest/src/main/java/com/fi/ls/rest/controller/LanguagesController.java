package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.language.*;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.facade.LanguageFacade;
import com.fi.ls.facade.LecturerFacade;
import com.fi.ls.rest.ApiEndpoints;
import com.fi.ls.rest.assembler.LanguageResourceAssembler;
import com.fi.ls.rest.exception.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LANGUAGES)
public class LanguagesController {

	final static Logger logger = LoggerFactory.getLogger(LanguagesController.class);

	@Inject
	private LanguageFacade languageFacade;
        
        @Inject
	private LecturerFacade lecturerFacade;
        
        @Inject
        private LanguageResourceAssembler languageResourceAssembler;

	/**
	 * get all the languages (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/languages/
	 * 
         * @param webRequest
	 * @return list of LanguageDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LanguageDTO>>> getLanguages(WebRequest webRequest) {

            logger.debug("rest getLanguages()");
                
            Collection<LanguageDTO> languagesDTO = languageFacade.getAllLanguages();
            Collection<Resource<LanguageDTO>> languageResourceCollection = new ArrayList();

            for (LanguageDTO l : languagesDTO) {
                languageResourceCollection.add(languageResourceAssembler.toResource(l));
            }

            Resources<Resource<LanguageDTO>> languagesResources = new Resources<>(languageResourceCollection);
            languagesResources.add(linkTo(this.getClass()).withSelfRel());
            languagesResources.add(linkTo(LanguagesController.class).slash("create").withRel("POST"));

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(languagesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(languagesResources);
	}
        
        /**
         * get language by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/languages/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<LanguageDTO>> getLanguage(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getLanguage({})", id);

            Optional<LanguageDTO> languageDTO = languageFacade.getLanguageById(id);
            if(!languageDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<LanguageDTO> resource = languageResourceAssembler.toResource(languageDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(languageDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }
        
        /**
         * delete language
         * curl -i -X DELETE http://localhost:8080/pa165/rest/languages/{id}
         * 
         * @param id 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteLanguage(@PathVariable("id") long id)  {
            logger.debug("rest deleteLanguage({})", id);

            Optional<LanguageDTO> language = languageFacade.getLanguageById(id);
            if(!language.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = languageFacade.deleteLanguage(language.get());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
        
        /**
         * create language
         * curl -X POST -i -H "Content-Type: application/json" --data '{"language":"Created","lecturerId":"2","proficiencyLevel":"B1"}' http://localhost:8080/pa165/rest/languages/create
         * NOTE: You might need to escape " and ' characters
         * 
         * @param language
         * @return 
         */
        @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final LanguageDTO createLanguage(@RequestBody LanguageCreateDTO language) {

            logger.debug("rest createLanguage()");
            
            Optional<LecturerDTO> lecturer = lecturerFacade.getLecturerById(language.getLecturerId());
            if(!lecturer.isPresent())
                throw new InvalidParameterException();
            
            LanguageDTO filledLanguage = new LanguageDTO();
            filledLanguage.setLanguage(language.getLanguage());
            filledLanguage.setProficiencyLevel(language.getProficiencyLevel());
            filledLanguage.setLecturer(lecturer.get());
            
            Optional<LanguageDTO> created = languageFacade.createLanguage(filledLanguage);

            if(created.isPresent())
                return created.get();
            else
                throw new InvalidParameterException();
        }
        
        /**
         * update language
         * curl -X PUT -i -H "Content-Type: application/json" --data '{"id":2,"language":"Updated","proficiencyLevel":"B1"}' http://localhost:8080/pa165/rest/languages/update
         * NOTE: You might need to escape " and ' characters
         * 
         * @param language
         * @return 
         */
        @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final LanguageDTO updateCourse(@RequestBody LanguageDTO language) {

            logger.debug("rest updateLanguage()");
            
            Optional<LanguageDTO> target = languageFacade.getLanguageById(language.getId());
            if(!target.isPresent())
                throw new ResourceNotFoundException();
            
            language.setLecturer(target.get().getLecturer());
            Optional<LanguageDTO> updated = languageFacade.updateLanguage(language);
            
            if(updated.isPresent())
                return updated.get();
            else
                throw new InvalidParameterException();
        }
}
