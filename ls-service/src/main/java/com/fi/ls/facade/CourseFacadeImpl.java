package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.CourseService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
@Transactional
public class CourseFacadeImpl implements CourseFacade {

	private CourseService courseService;
	private BeanMapping beanMapping;

	@Inject
	public CourseFacadeImpl(BeanMapping beanMapping, CourseService courseService) {
		this.courseService = courseService;
		this.beanMapping = beanMapping;
	}

	public List<CourseDTO> getAllCourses() {
		return beanMapping.mapTo(courseService.findAll(), CourseDTO.class);
	}

	public Optional<CourseDTO> getCourseById(Long id) {
		Optional<Course> course = Optional.of(courseService.findById(id));
		return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
	}

	@Override
	public Optional<CourseDTO> create(CourseCreateDTO c) {
		Optional<Course> course = Optional.of(courseService.create(beanMapping.mapTo(c, Course.class).get()));
		return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
	}

	@Override
	public Optional<CourseDTO> updateCourse(Long courseId) {
		Optional<Course> course = Optional.of(courseService.update(courseService.findById(courseId)));
		return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
	}

	@Override
	public void deleteCourse(Long courseId) {
		courseService.remove(courseService.findById(courseId));
	}

	@Override
	public Optional<CourseDTO> getCourseByName(String name) {
		Optional<Course> course = Optional.of(courseService.findByName(""));
		return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
	}

	@Override
	public void addLecture(CourseDTO c, LectureDTO l) {
		courseService.addLecture(beanMapping.mapTo(c, Course.class).get(), beanMapping.mapTo(l, Lecture.class).get());
	}

	@Override
	public void addLectures(CourseDTO c, List<LectureDTO> l) {
		courseService.addLectures(beanMapping.mapTo(c, Course.class).get(), beanMapping.mapTo(l, Lecture.class));
	}

}
