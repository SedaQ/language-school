package com.fi.ls.rest.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.facade.CourseFacade;
import com.fi.ls.rest.ApiEndpoints;
import com.fi.ls.rest.assembler.CourseResourceAssembler;
import com.fi.ls.rest.assembler.LectureResourceAssembler;
import com.fi.ls.rest.exception.ResourceNotFoundException;
import com.fi.ls.rest.exception.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_COURSES)
public class CoursesController {

	final static Logger logger = LoggerFactory.getLogger(CoursesController.class);

	@Inject
	private CourseFacade courseFacade;
        
        @Inject
        private CourseResourceAssembler courseResourceAssembler;
        
        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        
	/**
	 * get all the courses (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/courses
	 * 
         * @param webRequest
	 * @return list of CourseDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<CourseDTO>>> getCourses(WebRequest webRequest) {

            logger.debug("rest getCourses()");
                
            Collection<CourseDTO> coursesDTO = courseFacade.getAllCourses();
            Collection<Resource<CourseDTO>> courseResourceCollection = new ArrayList<>();

            for (CourseDTO c : coursesDTO) {
                courseResourceCollection.add(courseResourceAssembler.toResource(c));
            }

            Resources<Resource<CourseDTO>> coursesResources = new Resources<>(courseResourceCollection);
            coursesResources.add(linkTo(this.getClass()).withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(coursesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(coursesResources);
	}
        
        /**
         * get course by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/courses/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<CourseDTO>> getCourse(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getCourse({})", id);

            Optional<CourseDTO> courseDTO = courseFacade.getCourseById(id);
            if(!courseDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<CourseDTO> resource = courseResourceAssembler.toResource(courseDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(courseDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }
        
        /**
         * get course lectures by course id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/courses/{id}/lectures
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/lectures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LectureDTO>>> geCourseLectures(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest geCourseLectures({})", id);

            Optional<CourseDTO> courseDTO = courseFacade.getCourseById(id);
            if(!courseDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();
            for (LectureDTO lect : courseDTO.get().getListOfLectures()) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }
            
            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).slash(courseDTO.get().getId()).slash("lectures").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
        }
        
        /**
         * delete course
         * curl -i -X DELETE http://localhost:8080/pa165/rest/courses/{id}
         * 
         * @param id 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteCourse(@PathVariable("id") long id)  {
            logger.debug("rest deleteCourse({})", id);

            Optional<CourseDTO> course = courseFacade.getCourseById(id);
            if(!course.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = courseFacade.deleteCourse(course.get().getId());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
}
