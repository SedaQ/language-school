package com.fi.ls.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Inject
	private CourseDao courseDao;

        public CourseServiceImpl() {
        }
        
        public CourseServiceImpl(CourseDao courseDao) {
            this.courseDao = courseDao;
        }
        
	@Override
	public Course create(Course c) {
		courseDao.create(c);
		return c;
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
	public Course findByName(String name) {
		return courseDao.findByName(name);
	}

	@Override
	public List<Course> findAll() {
		return courseDao.findAll();
	}

	@Override
	public void synchronizeLectures() {
		// TODO Auto-generated method stub
	}

	@Override
	public void addLecture(Course c, Lecture l) {
		c.addLecture(l);
	}

	@Override
	public void addLectures(Course c, List<Lecture> l) {
		c.addLectures(l);
	}

}
