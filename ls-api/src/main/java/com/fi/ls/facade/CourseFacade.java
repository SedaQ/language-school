package com.fi.ls.facade;

import java.util.List;

import com.fi.ls.dto.CourseDTO;

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
	 */
	public void create(CourseDTO c);

	/**
	 * finds specific course by id
	 * 
	 * @param id
	 *            of a course that would be returned
	 * @return specific course by id
	 */
	public CourseDTO findById(Long id);

	/**
	 * updates given course
	 * 
	 * @param c
	 *            course that has to be updated
	 * @return updated course
	 */
	public CourseDTO update(CourseDTO c);

	/**
	 * removes given course
	 * 
	 * @param c
	 *            course that has to be removed
	 */
	public void remove(CourseDTO c);

	/**
	 * Returns all courses in language school
	 * 
	 * @return List of courses which are in language school
	 */
	public List<CourseDTO> findAll();
}
