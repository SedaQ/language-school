package com.fi.ls.facade;

import javax.transaction.Transactional;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.CourseService;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.testng.annotations.BeforeMethod;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourseFacadeTest extends AbstractTestNGSpringContextTests {

	@Mock
	CourseService courseService;

	@Mock
	BeanMapping beanMapping;

	CourseFacade courseFacade;

	CourseCreateDTO courseCreateDTO1;
	CourseDTO courseDTO1;
	CourseDTO courseDTO2;

	LectureDTO lectureDTO1;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		doReturn(Optional.of(new Course())).when(beanMapping).mapTo(any(CourseCreateDTO.class), eq(Course.class));
		doReturn(Optional.of(new Course())).when(beanMapping).mapTo(any(CourseDTO.class), eq(Course.class));
		doReturn(Optional.of(new CourseDTO())).when(beanMapping).mapTo(any(Course.class), eq(CourseDTO.class));
		doReturn(Optional.of(new Course())).when(beanMapping).mapTo(any(CourseDTO.class), eq(Course.class));
		doReturn(Optional.of(new Lecture())).when(beanMapping).mapTo(any(LectureDTO.class), eq(Lecture.class));

		courseFacade = new CourseFacadeImpl(courseService, beanMapping);
	}

	@BeforeMethod
	public void init() {
		courseCreateDTO1 = new CourseCreateDTO();
		courseDTO1 = new CourseDTO();
		courseDTO2 = new CourseDTO();
		lectureDTO1 = new LectureDTO();
	}

	@AfterMethod
	public void tearDown() {
		reset(courseService);
	}

	@Test
	public void testCreate() {
		courseFacade.create(courseCreateDTO1);
		verify(courseService, times(1)).create(beanMapping.mapTo(courseCreateDTO1, Course.class).get());
	}

	@Test
	public void testCreateCourseThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).create(any(Course.class));
		Optional<CourseDTO> output = courseFacade.create(courseCreateDTO1);
		assertEquals(output, Optional.empty());
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testCreateCourseNull() {
		courseFacade.create(null);
		fail("testCreateCourseNull expected IllegalArgumentException");
	}

	@Test
	public void testGetCourseById() {
		courseFacade.getCourseById(Long.MAX_VALUE);
		verify(courseService, times(1)).findById(any(Long.class));
	}

	@Test
	public void testGetCourseByIdThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).findById(any(Long.class));
		Optional<CourseDTO> output = courseFacade.getCourseById(Long.MAX_VALUE);
		assertEquals(output, Optional.empty());
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testGetCourseByIdNull() {
		courseFacade.getCourseById(null);
		fail("testGetCourseByIdNull expectedExpected IllegalArgumentException");
	}

	@Test
	public void testUpdateCourse() {
		courseFacade.updateCourse(Long.MAX_VALUE);
		verify(courseService, times(1)).update(any(Course.class));
	}

	@Test
	public void testUpdateCourseThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).update(any(Course.class));

		Optional<CourseDTO> output = courseFacade.updateCourse(Long.MAX_VALUE);
		assertEquals(output, Optional.empty());
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testUpdateCourseNull() {
		courseFacade.updateCourse(null);
		fail("testUpdateCourseNull expected IllegalArgumentException");
	}

	@Test
	public void testDeleteCourse() {
		courseFacade.deleteCourse(Long.MAX_VALUE);
		verify(courseService, times(1)).remove(any(Course.class));
	}

	@Test
	public void testDeleteCourseThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).remove(any(Course.class));
		Boolean output = courseFacade.deleteCourse(Long.MAX_VALUE);
		assertFalse(output);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testDeleteCourseNull() {
		courseFacade.deleteCourse(null);
		fail("testDeleteCourseNull expected IllegalArgumentException");
	}

	@Test
	public void testGetAllCourses() {
		courseFacade.getAllCourses();
		verify(courseService, times(1)).findAll();
	}

	@Test
	public void testGetAllCoursesThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).findAll();
		List<CourseDTO> output = courseFacade.getAllCourses();
		assertEquals(output, Collections.EMPTY_LIST);
	}

	@Test
	public void testGetCourseByName() {
		courseFacade.getCourseByName("Test name");
		verify(courseService, times(1)).findByName(any(String.class));
	}

	@Test
	public void testGetCourseByNameThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).findByName(any(String.class));
		Optional<CourseDTO> output = courseFacade.getCourseByName("testName");
		assertEquals(output, Optional.empty());
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testGetCourseByNameNull() {
		courseFacade.getCourseByName(null);
		fail("testGetCourseByNameNull method expected IllegalArgumentException");
	}

	@Test
	public void testAddLecture() {
		courseFacade.addLecture(courseDTO1, lectureDTO1);
		verify(courseService, times(1)).addLecture(any(Course.class), any(Lecture.class));
	}

	@Test
	public void testAddLectureThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).addLecture(any(Course.class), any(Lecture.class));
		Boolean output = courseFacade.addLecture(courseDTO1, lectureDTO1);
		assertFalse(output);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLectureCourseNull() {
		courseFacade.addLecture(null, lectureDTO1);
		fail("Expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddCourseLectureNull() {
		courseFacade.addLecture(courseDTO1, null);
		fail("Expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddCourseLectureBothNull() {
		courseFacade.addLecture(null, null);
		fail("Expected IllegalArgumentException");
	}

	@Test
	public void testAddLectures() {
		courseFacade.addLectures(courseDTO1, Arrays.asList(lectureDTO1));
		verify(courseService, times(1)).addLectures(any(Course.class), any(List.class));
	}

	@Test
	public void testAddLecturesThrows() {
		doThrow(new ServiceLayerException("")).when(courseService).addLectures(any(Course.class), any(List.class));
		Boolean output = courseFacade.addLectures(courseDTO1, Arrays.asList(lectureDTO1));
		assertFalse(output);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLecturesCourseNull() {
		courseFacade.addLectures(null, Arrays.asList(lectureDTO1));
		fail("Expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLecturesLecturesNull() {
		courseFacade.addLectures(courseDTO1, null);
		fail("Expected IllegalArgumentException");
	}

}
