package com.fi.ls.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.facade.LanguageFacade;
import com.fi.ls.rest.ApiEndpoints;
import com.fi.ls.rest.assembler.LanguageResourceAssembler;
import com.fi.ls.rest.exception.ResourceNotFoundException;
import com.fi.ls.rest.exception.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LANGUAGES)
public class LanguagesController {

	final static Logger logger = LoggerFactory.getLogger(LanguagesController.class);

	@Inject
	private LanguageFacade languageFacade;
        
        @Inject
        private LanguageResourceAssembler languageResourceAssembler;

	/**
	 * get all the languages (with HTTP caching)
         * curl -i -X GET http://localhost:8080/ls-rest/languages/
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
            languagesResources.add(linkTo(LanguageResourceAssembler.class).withSelfRel());

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
         * curl -i -X GET http://localhost:8080/ls-rest/languages/{id}
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
         * curl -i -X DELETE http://localhost:8080/ls-rest/languages/{id}
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
}
