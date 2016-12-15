package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface CourseFacade {

	/**
	 * create new course in database
	 * 
	 * @param c
	 *            specific Course to be created
	 * @return created course
	 */
	public Optional<CourseDTO> create(CourseCreateDTO c);

	/**
	 * finds specific course by id
	 * 
	 * @param id
	 *            of a course that would be returned
	 * @return specific course by id
	 */
	public Optional<CourseDTO> getCourseById(Long id);

	/**
	 * updates given course
	 * 
	 * @param c
	 *            course that has to be updated
	 * @return updated course
	 */
	public Optional<CourseDTO> updateCourse(CourseDTO c);

	/**
	 * removes given course
	 * 
	 * @param id
	 *            course that has to be removed
         * @return true, if successfully removed
	 */
	public Boolean deleteCourse(Long id);

	/**
	 * finds specific course by name
	 * 
	 * @param name
	 *            of a course that would be returned
	 * @return specific course by name
	 */
	public Optional<CourseDTO> getCourseByName(String name);

	/**
	 * Returns all courses in language school
	 * 
	 * @return List of courses which are in language school
	 */
	public List<CourseDTO> getAllCourses();
	
	/**
	 * add lecture to course
	 * @param l lecture which will be added to course
	 * @param c course to which lecture will be added
         * @return true, if successfully added
	 */
	public Boolean addLecture(CourseDTO c, LectureDTO l);
	
	/**
	 * add lectures to course
	 * @param l list of lectures which will be added to course
	 * @param c course to which lectures will be added
         * @return true, if successfully added
	 */
	public Boolean addLectures(CourseDTO c, List<LectureDTO> l);
	
}