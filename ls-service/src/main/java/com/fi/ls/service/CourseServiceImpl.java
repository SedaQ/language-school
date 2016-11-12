package com.fi.ls.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Inject
	private CourseDao courseDao;

	@Override
	public void create(Course c) {
		courseDao.create(c);
	}

	@Override
	public Course findById(Long id) {
		return courseDao.findById(id);
	}

	@Override
	public Course update(Course c) {
		return courseDao.update(c);
	}

	@Override
	public void remove(Course c) {
		courseDao.remove(c);
	}

	@Override
	public List<Course> findAll() {
		return courseDao.findAll();
	}

}
