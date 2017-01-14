package com.fi.ls.rest.controller;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureCreateDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.facade.LectureFacade;
import com.fi.ls.rest.ApiEndpoints;
import com.fi.ls.rest.assembler.CourseResourceAssembler;
import com.fi.ls.rest.assembler.LectureResourceAssembler;
import com.fi.ls.rest.assembler.LecturerResourceAssembler;
import com.fi.ls.rest.assembler.StudentResourceAssembler;
import com.fi.ls.rest.exception.InvalidParameterException;
import com.fi.ls.rest.exception.ResourceNotFoundException;
import com.fi.ls.rest.exception.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@RestController
@RequestMapping(ApiEndpoints.ROOT_URI_LECTURES)
public class LecturesController {

	final static Logger logger = LoggerFactory.getLogger(LecturesController.class);

	@Inject
	private LectureFacade lectureFacade;
                
        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        
        @Inject
        private CourseResourceAssembler courseResourceAssembler;
        
        @Inject
        private LecturerResourceAssembler lecturerResourceAssembler;
        
        @Inject
        private StudentResourceAssembler studentResourceAssembler;
        
	/**
	 * get all the lectures (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lectures
	 * 
         * @param webRequest
	 * @return list of LectureDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LectureDTO>>> getLectures(WebRequest webRequest) {

            logger.debug("rest getLectures()");
                
            Collection<LectureDTO> lecturesDTO = lectureFacade.getAllLectures();
            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();

            for (LectureDTO lect : lecturesDTO) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }

            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).withSelfRel());
            lecturesResources.add(linkTo(LecturesController.class).slash("create").withRel("POST"));

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
	}
        
        /**
         * get lecture by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lectures/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<LectureDTO>> getLecture(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest getLecture({})", id);

            Optional<LectureDTO> lectureDTO = lectureFacade.getLectureById(id);
            if(!lectureDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<LectureDTO> resource = lectureResourceAssembler.toResource(lectureDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lectureDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }
        
        /**
         * get lecture lecturers by lecture id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lectures/{id}/lecturers
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/lecturers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LecturerDTO>>> geLectureLecturers(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest geLectureLecturers({})", id);

            Optional<LectureDTO> lectureDTO = lectureFacade.getLectureById(id);
            if(!lectureDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LecturerDTO>> lecturerResourceCollection = new ArrayList<>();
            for (LecturerDTO l : lectureDTO.get().getListOfLecturers()) {
                lecturerResourceCollection.add(lecturerResourceAssembler.toResource(l));
            }
            
            Resources<Resource<LecturerDTO>> lecturersResources = new Resources<>(lecturerResourceCollection);
            lecturersResources.add(linkTo(this.getClass()).slash(lectureDTO.get().getId()).slash("lecturers").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturersResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturersResources);
        }
        
        /**
         * get lecture lecturers by lecture id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lectures/{id}/lecturers
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<CourseDTO>>> geLectureCourses(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest geLectureCourses({})", id);

            Optional<LectureDTO> lectureDTO = lectureFacade.getLectureById(id);
            if(!lectureDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<CourseDTO>> courseResourceCollection = new ArrayList<>();
            for (CourseDTO c : lectureDTO.get().getListOfCourses()) {
                courseResourceCollection.add(courseResourceAssembler.toResource(c));
            }
            
            Resources<Resource<CourseDTO>> coursesResources = new Resources<>(courseResourceCollection);
            coursesResources.add(linkTo(this.getClass()).slash(lectureDTO.get().getId()).slash("courses").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(coursesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(coursesResources);
        }
        
        /**
         * get lecture students by lecture id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/lectures/{id}/students
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<StudentDTO>>> geLectureStudents(@PathVariable("id") long id, WebRequest webRequest) {
        
            logger.debug("rest geLectureStudents({})", id);

            Optional<LectureDTO> lectureDTO = lectureFacade.getLectureById(id);
            if(!lectureDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<StudentDTO>> studentResourceCollection = new ArrayList<>();
            for (StudentDTO s : lectureDTO.get().getListOfStudents()) {
                studentResourceCollection.add(studentResourceAssembler.toResource(s));
            }
            
            Resources<Resource<StudentDTO>> studentsResources = new Resources<>(studentResourceCollection);
            studentsResources.add(linkTo(this.getClass()).slash(lectureDTO.get().getId()).slash("students").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(studentsResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(studentsResources);
        }
        
        /**
         * delete lecture
         * curl -i -X DELETE http://localhost:8080/pa165/rest/lectures/{id}
         * 
         * @param id 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteLecture(@PathVariable("id") long id)  {
            logger.debug("rest deleteLecture({})", id);

            Optional<LectureDTO> lecture = lectureFacade.getLectureById(id);
            if(!lecture.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = lectureFacade.deleteLecture(lecture.get().getId());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
        
        /**
         * create lecture
         * 
         * @param lecture
         * @return 
         */
        @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final LectureDTO createCourse(@RequestBody LectureCreateDTO lecture) {

            logger.debug("rest createLecture()");

            Optional<LectureDTO> created = lectureFacade.createLecture(lecture);

            if(created.isPresent())
                return created.get();
            else
                throw new InvalidParameterException();
        }
        
        /**
         * update lecture
         * 
         * @param lecture
         * @return 
         */
        @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final LectureDTO updateCourse(@RequestBody LectureDTO lecture) {

            logger.debug("rest updateLecture()");
            
            Optional<LectureDTO> target = lectureFacade.getLectureById(lecture.getId());
            if(!target.isPresent())
                throw new ResourceNotFoundException();
            
            lecture.setListOfCourses(target.get().getListOfCourses());
            lecture.setListOfLecturers(target.get().getListOfLecturers());
            lecture.setListOfStudents(target.get().getListOfStudents());
            Optional<LectureDTO> updated = lectureFacade.updateLecture(lecture);
            
            if(updated.isPresent())
                return updated.get();
            else
                throw new InvalidParameterException();
        }
}
