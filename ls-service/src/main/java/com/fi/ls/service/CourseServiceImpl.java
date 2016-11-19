package com.fi.ls.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	public CourseServiceImpl() {
	}

	@Inject
	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Course create(Course c) {
		if (c == null)
			throw new IllegalArgumentException("Course parameter is null");
		courseDao.create(c);
		return c;
	}

	@Override
	public Course findById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Course id parameter is null");
		return courseDao.findById(id);
	}

	@Override
	public Course update(Course c) {
		if (c == null)
			throw new IllegalArgumentException("Course c parameter is null");
		return courseDao.update(c);
	}

	@Override
	public void remove(Course c) {
		if (c == null)
			throw new IllegalArgumentException("Course c parameter is null");
		courseDao.remove(c);
	}

	@Override
	public Course findByName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Course name parameter is null");
		return courseDao.findByName(name);
	}

	@Override
	public List<Course> findAll() {
		return courseDao.findAll();
	}

	@Override
	public void addLecture(Course c, Lecture l) {
		if (c == null || l == null)
			throw new IllegalArgumentException("Course or Lecture parameter is null");
		c.addLecture(l);
		courseDao.update(c);
	}

	@Override
	public void addLectures(Course c, List<Lecture> l) {
		if (c == null || l == null)
			throw new IllegalArgumentException("Course or Lecture parameter is null");
		c.addLectures(l);
		courseDao.update(c);
	}

}
