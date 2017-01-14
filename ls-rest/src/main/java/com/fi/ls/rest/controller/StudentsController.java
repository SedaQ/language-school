package com.fi.ls.rest.controller;

import com.fi.ls.dto.lecture.LectureDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.facade.StudentFacade;
import com.fi.ls.rest.ApiEndpoints;
import com.fi.ls.rest.assembler.LectureResourceAssembler;
import com.fi.ls.rest.assembler.StudentResourceAssembler;
import com.fi.ls.rest.exception.ResourceNotFoundException;
import com.fi.ls.rest.exception.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_STUDENTS)
public class StudentsController {

	final static Logger logger = LoggerFactory.getLogger(StudentsController.class);

	@Inject
	private StudentFacade studentFacade;
        
        @Inject
        private StudentResourceAssembler studentResourceAssembler;

        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        
	/**
	 * get all the students (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/students
	 * 
         * @param webRequest
	 * @return list of StudentsDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<StudentDTO>>> getLecturers(WebRequest webRequest) {

            logger.debug("rest getStudents()");
                
            Collection<StudentDTO> studentsDTO = studentFacade.getAllStudents();
            Collection<Resource<StudentDTO>> studentResourceCollection = new ArrayList<>();

            for (StudentDTO s : studentsDTO) {
                studentResourceCollection.add(studentResourceAssembler.toResource(s));
            }

            Resources<Resource<StudentDTO>> studentsResources = new Resources<>(studentResourceCollection);
            studentsResources.add(linkTo(this.getClass()).withSelfRel());
            studentsResources.add(linkTo(StudentsController.class).slash("create").withRel("POST"));

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(studentsResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(studentsResources);
	}
        
        /**
         * get student by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/students/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<StudentDTO>> getStudent(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getStudent({})", id);

            Optional<StudentDTO> studentDTO = studentFacade.getStudentById(id);
            if(!studentDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<StudentDTO> resource = studentResourceAssembler.toResource(studentDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(studentDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }
        
        /**
         * get student lectures by student id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/students/{id}/lectures
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/lectures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LectureDTO>>> getStudentLectures(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getStudentLectures({})", id);

            Optional<StudentDTO> studentDTO = studentFacade.getStudentById(id);
            if(!studentDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();
            for (LectureDTO lect : studentDTO.get().getListOfLectures()) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }
            
            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).slash(studentDTO.get().getId()).slash("lectures").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
        }
        
        /**
         * delete student
         * curl -i -X DELETE http://localhost:8080/pa165/rest/students/{id}
         * 
         * @param id 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteStudent(@PathVariable("id") long id)  {
            logger.debug("rest deleteStudent({})", id);

            Optional<StudentDTO> student = studentFacade.getStudentById(id);
            if(!student.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = studentFacade.deleteStudent(student.get());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
}
