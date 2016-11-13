package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import com.fi.ls.dto.CourseDTO;
import com.fi.ls.dto.LectureDTO;
import com.fi.ls.dto.LecturerDTO;

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
	 * @return
	 */
	public Optional<Long> create(CourseDTO c);

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
	 * @param id
	 *            course that has to be updated
	 * @return updated course
	 */
	public Optional<CourseDTO> updateCourse(Long id);

	/**
	 * removes given course
	 * 
	 * @param id
	 *            course that has to be removed
	 */
	public void deleteCourse(Long id);

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
	 */
	public void addLecture(CourseDTO c, LectureDTO l);
	
	/**
	 * add lectures to course
	 * @param l list of lectures which will be added to course
	 * @param c course to which lectures will be added
	 */
	public void addLectures(CourseDTO c, List<LectureDTO> l);
	
}