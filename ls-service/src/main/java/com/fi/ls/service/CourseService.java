package com.fi.ls.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public interface CourseService {

	/**
	 * create new course in database
	 * 
	 * @param c
	 *            specific Course to be created
	 * @return
	 */
	public Course create(Course c);

	/**
	 * finds specific course by id
	 * 
	 * @param id
	 *            of a course that would be returned
	 * @return specific course by id
	 */
	public Course findById(Long id);

	/**
	 * finds specific course by name
	 * @param name of a course that would be returned
	 * @return specific course by name 
	 */
	public Course findByName(String name);
	
	/**
	 * updates given course
	 * 
	 * @param c
	 *            course that has to be updated
	 * @return updated course
	 */
	public Course update(Course c);

	/**
	 * removes given course
	 * 
	 * @param c
	 *            course that has to be removed
	 */
	public void remove(Course c);

	/**
	 * Returns all courses in language school
	 * 
	 * @return List of courses which are in language school
	 */
	public List<Course> findAll();
	
	/**
	 * add lecture to course
	 * @param l lecture which will be added to course
	 * @param c course to which lecture will be added
	 */
	public void addLecture(Course c, Lecture l);
	
	/**
	 * add lectures to course
	 * @param l list of lectures which will be added to course
	 * @param c course to which lectures will be added
	 */
	public void addLectures(Course c, List<Lecture> l);	
	
}
