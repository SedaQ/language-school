package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fi.ls.dto.CourseDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.CourseService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
@Transactional
public class CourseFacadeImpl implements CourseFacade {

	@Inject
	private CourseService courseService;

	@Inject
	private BeanMapping beanMapping;

	public List<CourseDTO> getAllCourses() {
		return beanMapping.mapTo(courseService.findAll(), CourseDTO.class);
	}

	public Optional<CourseDTO> getCourseById(Long id) {
		Optional<Course> course = Optional.of(courseService.findById(id));
		return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
	}

	@Override
	public void create(CourseDTO c) {

	}

	@Override
	public Optional<CourseDTO> updateCourse(Long courseId) {
		return Optional.empty();
	}

	@Override
	public void deleteCourse(Long courseId) {
	}

}
