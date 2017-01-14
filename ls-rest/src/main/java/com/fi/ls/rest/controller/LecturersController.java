package com.fi.ls.rest.controller;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.*;
import com.fi.ls.enums.UserRoles;
import com.fi.ls.facade.LSUserFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.facade.LecturerFacade;
import com.fi.ls.rest.ApiEndpoints;
import com.fi.ls.rest.assembler.LanguageResourceAssembler;
import com.fi.ls.rest.assembler.LectureResourceAssembler;
import com.fi.ls.rest.assembler.LecturerResourceAssembler;
import com.fi.ls.rest.exception.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LECTURERS)
public class LecturersController {

	final static Logger logger = LoggerFactory.getLogger(LecturersController.class);

	@Inject
	private LecturerFacade lecturerFacade;
        
        @Inject
	private LSUserFacade userFacade;
        
        @Inject
        private LecturerResourceAssembler lecturerResourceAssembler;
        
        @Inject
        private LanguageResourceAssembler languageResourceAssembler;
        
        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        
	/**
	 * get all the lecturers (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lecturers
	 * 
         * @param webRequest
	 * @return list of LecturerDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LecturerDTO>>> getLecturers(WebRequest webRequest) {

            logger.debug("rest getLecturers()");
                
            Collection<LecturerDTO> lecturersDTO = lecturerFacade.getAllLecturers();
            Collection<Resource<LecturerDTO>> languageResourceCollection = new ArrayList<>();

            for (LecturerDTO l : lecturersDTO) {
                languageResourceCollection.add(lecturerResourceAssembler.toResource(l));
            }

            Resources<Resource<LecturerDTO>> lecturersResources = new Resources<>(languageResourceCollection);
            lecturersResources.add(linkTo(this.getClass()).withSelfRel());
            lecturersResources.add(linkTo(LecturersController.class).slash("create").withRel("POST"));

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturersResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturersResources);
	}
        
        /**
         * get lecturer by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lecturers/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<LecturerDTO>> getLecturer(@PathVariable("id") long id, WebRequest webRequest) {
        
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
        
        /**
         * get lecturer languages by lecturer id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lecturers/{id}/languages
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/languages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LanguageDTO>>> getLecturerLanguages(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getLecturerLanguages({})", id);

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.getLecturerById(id);
            if(!lecturerDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LanguageDTO>> languageResourceCollection = new ArrayList<>();
            for (LanguageDTO lang : lecturerDTO.get().getListOfLanguages()) {
                languageResourceCollection.add(languageResourceAssembler.toResource(lang));
            }
            
            Resources<Resource<LanguageDTO>> languagesResources = new Resources<>(languageResourceCollection);
            languagesResources.add(linkTo(this.getClass()).slash(lecturerDTO.get().getId()).slash("languages").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(languagesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(languagesResources);
        }
        
        /**
         * get lecturer lectures by lecturer id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lecturers/{id}/lectures
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/lectures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LectureDTO>>> getLecturerLectures(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getLecturerLectures({})", id);

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.getLecturerById(id);
            if(!lecturerDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();
            for (LectureDTO lect : lecturerDTO.get().getListOfLectures()) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }
            
            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).slash(lecturerDTO.get().getId()).slash("lectures").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
        }
        
        /**
         * delete lecturer
         * curl -i -X DELETE http://localhost:8080/pa165/rest/lecturers/{id}
         * 
         * @param id 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteLecturer(@PathVariable("id") long id)  {
            logger.debug("rest deleteLecturer({})", id);

            Optional<LecturerDTO> lecturer = lecturerFacade.getLecturerById(id);
            if(!lecturer.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = lecturerFacade.deleteLecturer(lecturer.get());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
        
        /**
         * create lecturer
         * curl -X POST -i -H "Content-Type: application/json" --data '{"nickname":"ju","firstName":"hele","surname":"nedele","email":"juhele@nedele.muf"}' http://localhost:8080/pa165/rest/lecturers/create
         * NOTE: You might need to escape " and ' characters
         * 
         * @param lecturer
         * @return 
         */
        @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final LecturerDTO createLecturer(@RequestBody LecturerDTO lecturer) {

            logger.debug("rest createLecturer()");
            
            lecturer.setUserRole(UserRoles.ROLE_LECTURER.name());
            Boolean created = lecturerFacade.registerUser(lecturer, "default");
            
            if(created) {
                Long lecturerId = userFacade.getUserByEmail(lecturer.getEmail()).get().getId();
                return lecturerFacade.getLecturerById(lecturerId).get();
            }
            else
                throw new InvalidParameterException();
        }
        
        /**
         * update lecturer
         * curl -X PUT -i -H "Content-Type: application/json" --data '{"id":2,"nickname":"Updated","firstName":"test","surname":"testS"}' http://localhost:8080/pa165/rest/lecturers/update
         * NOTE: You might need to escape " and ' characters
         * 
         * @param lecturer
         * @return 
         */
        @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final LecturerDTO updateLecturer(@RequestBody LecturerDTO lecturer) {

            logger.debug("rest updateLecturer()");
            
            Optional<LecturerDTO> target = lecturerFacade.getLecturerById(lecturer.getId());
            if(!target.isPresent())
                throw new ResourceNotFoundException();
            
            lecturer.setListOfLectures(target.get().getListOfLectures());
            lecturer.setListOfLanguages(target.get().getListOfLanguages());
            lecturer.setEmail(target.get().getEmail());
            lecturer.setPasswordHash(target.get().getPasswordHash());
            lecturer.setUserRole(target.get().getUserRole());
            Optional<LecturerDTO> updated = lecturerFacade.updateLecturer(lecturer);
            
            if(updated.isPresent())
                return updated.get();
            else
                throw new InvalidParameterException();
        }
}
