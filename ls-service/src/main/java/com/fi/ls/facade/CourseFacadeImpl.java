package com.fi.ls.facade;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.CourseService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
@Transactional
public class CourseFacadeImpl implements CourseFacade {

	private final Logger logger = LoggerFactory.getLogger(CourseFacadeImpl.class);

	private CourseService courseService;
	private BeanMapping beanMapping;

	@Inject
	public CourseFacadeImpl(CourseService courseService, BeanMapping beanMapping) {
		this.courseService = courseService;
		this.beanMapping = beanMapping;
	}

	public List<CourseDTO> getAllCourses() {
		try {
			return beanMapping.mapTo(courseService.findAll(), CourseDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getAllCourses method invokes exception: " + ex);
			return Collections.emptyList();
		}
	}

	public Optional<CourseDTO> getCourseById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id parameter is null");
		try {
			Optional<Course> course = Optional.of(courseService.findById(id));
			return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getCourseById method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<CourseDTO> create(CourseCreateDTO c) {
		if (c == null)
			throw new IllegalArgumentException("CourseCreateDTO c parameter is null");
		try {
			Optional<Course> course = Optional.of(courseService.create(beanMapping.mapTo(c, Course.class).get()));
			return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("create method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<CourseDTO> updateCourse(Long courseId) {
		if (courseId == null)
			throw new IllegalArgumentException("courseId parameter is null in updateCourse method");
		try {
			Optional<Course> course = Optional.of(courseService.update(courseService.findById(courseId)));
			return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("updateCourse method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Boolean deleteCourse(Long courseId) {
		if (courseId == null)
			throw new IllegalArgumentException("courseId parameter is null in deleteCourse method");
		try {
			courseService.remove(courseService.findById(courseId));
                        return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("deleteCourse method invokes exception: " + ex);
                        return false;
		}
	}

	@Override
	public Optional<CourseDTO> getCourseByName(String name) {
		if (name == null)
			throw new IllegalArgumentException("String parameter is null in deleteCourse method");
		try {
			Optional<Course> course = Optional.of(courseService.findByName(""));
			return course.isPresent() ? beanMapping.mapTo(course.get(), CourseDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getCourseByName method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Boolean addLecture(CourseDTO c, LectureDTO l) {
		if (c == null || l == null)
			throw new IllegalArgumentException("CourseDTO c parameter or LectureDTO l is null in addLecture method");
		try {
			courseService.addLecture(beanMapping.mapTo(c, Course.class).get(),
					beanMapping.mapTo(l, Lecture.class).get());
                        return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("addLecture method invokes exception: " + ex);
                        return false;
		}
	}

	@Override
	public Boolean addLectures(CourseDTO c, List<LectureDTO> l) {
		if (c == null || l == null)
			throw new IllegalArgumentException(
					"CourseDTO c parameter or List<LectureDTO> l is null in addLectures method");
		try {
			courseService.addLectures(beanMapping.mapTo(c, Course.class).get(), beanMapping.mapTo(l, Lecture.class));
                        return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("addLectures method invokes exception: " + ex);
                        return false;
		}
	}

}
