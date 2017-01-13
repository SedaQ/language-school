package com.fi.ls.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;
import java.util.Set;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	@Inject
	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Course create(Course c) {
		if (c == null)
			throw new IllegalArgumentException("Course parameter is null");
		try {
			courseDao.create(c);
			return c;
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with creating Course, see inner exception.", ex);
		}
	}

	@Override
	public Course findById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Course id parameter is null");
		try {
			return courseDao.findById(id);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Course, see inner exception.", ex);
		}
	}

	@Override
	public Course update(Course c) {
		if (c == null)
			throw new IllegalArgumentException("Course c parameter is null");
		try {
			return courseDao.update(c);
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with updating Course, see inner exception.", ex);
		}
	}

	@Override
	public void remove(Course c) {
		if (c == null)
			throw new IllegalArgumentException("Course c parameter is null");
		try {
			courseDao.remove(c);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with removing Course, see inner exception.", ex);
		}
	}

	@Override
	public Course findByName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Course name parameter is null");
		try {
			return courseDao.findByName(name);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Course, see inner exception.", ex);
		}
	}

	@Override
	public Set<Course> findAll() {
		try {
			return courseDao.findAll();
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Courses, see inner exception.", ex);
		}
	}

	@Override
	public void addLecture(Course c, Lecture l) {
		if (c == null || l == null)
			throw new IllegalArgumentException("Course or Lecture parameter is null");
		c.addLecture(l);
		courseDao.update(c);
	}

	@Override
	public void addLectures(Course c, Set<Lecture> l) {
		if (c == null || l == null)
			throw new IllegalArgumentException("Course or Lecture parameter is null");
		c.addLectures(l);
		courseDao.update(c);
	}

}
