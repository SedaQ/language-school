package com.fi.ls.service;

import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.enums.ProficiencyLevel;

import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class CourseServiceTest {

	@Mock
	private CourseDao courseDao;

	private CourseService courseService;

	private Course c;
	private Course c2;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		courseService = new CourseServiceImpl(courseDao);
	}

	@BeforeMethod
	public void init() {
		c = new Course();
		c.setName("testName");
		c.setProficiencyLevel(ProficiencyLevel.A1);

		c2 = new Course();
		c2.setName("testName c2");
		c2.setProficiencyLevel(ProficiencyLevel.C2);
	}

	@Test
	public void testCreate() {
		courseService.create(c);
		verify(courseDao, times(1)).create(c);
	}

	@Test(expectedExceptions = { DataAccessException.class })
	public void testCreateThrows() throws DataAccessException {
		doThrow(new PersistenceException("")).when(courseDao).create(any(Course.class));
		Course c1 = new Course();
		c1.setName("testName");
		c1.setProficiencyLevel(ProficiencyLevel.A1);
		courseService.create(c1);

		fail("Expected DataAccessException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testCreateNull() {
		courseService.create(null);

		fail("Expected IllegalArgumentException");
	}

	@Test
	public void testFindAll() {
		courseService.findAll();
		verify(courseDao, times(1)).findAll();
	}

	@Test(expectedExceptions = { DataAccessException.class })
	public void testFindAllThrows() throws DataAccessException {
		doThrow(new PersistenceException("")).when(courseDao).findAll();
		courseService.findAll();

		fail("Expected DataAccessException");
	}

	@Test
	public void testfindById() {
		courseService.findById(Long.MAX_VALUE);
		verify(courseDao, times(1)).findById(Long.MAX_VALUE);
	}

	@Test(expectedExceptions = { DataAccessException.class })
	public void testfindByIdThrows() throws DataAccessException {
		doThrow(new PersistenceException("")).when(courseDao).findById(any(Long.class));
		courseService.findById(1L);

		fail("Expected DataAccessException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testfindByIdNull() {
		courseService.findById(null);

		fail("Expected IllegalArgumentException");
	}

	@Test
	public void testUpdate() {
		c.setName("NamChangedFirstTime");
		courseService.update(c);

		verify(courseDao, times(1)).update(c);
	}

	@Test
	public void testFindByName() {
		courseService.findByName("courseName");
		verify(courseDao, times(1)).findByName("courseName");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testFindByNameNull() {
		courseService.findByName(null);
		fail("Expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { DataAccessException.class })
	public void testUpdateThrows() throws DataAccessException {
		doThrow(new PersistenceException("")).when(courseDao).update(any(Course.class));
		c.setName("NameChanged");
		courseService.update(c);

		fail("Expected DataAccessException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testUpdateNull() {
		courseService.update(null);

		fail("Expected IllegalArgumentException");
	}

	@Test
	public void testRemove() {
		courseService.remove(c2);
		verify(courseDao, times(1)).remove(c2);
	}

	@Test(expectedExceptions = { DataAccessException.class })
	public void testRemoveThrows() throws DataAccessException {
		doThrow(new PersistenceException("")).when(courseDao).remove(any(Course.class));
		courseService.remove(c2);

		fail("Expected DataAccessException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testRemoveNull() {
		courseService.remove(null);

		fail("Expected IllegalArgumentException");
	}

	@Test
	public void testAddLecture() {
		Course course = new Course();
		course.setName("testNameCourse");
		course.setProficiencyLevel(ProficiencyLevel.B2);

		Lecture lecture1 = new Lecture();
		lecture1.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 16, 0));
		lecture1.setClassroomId("B403");
		lecture1.setTopic("English");

		Lecture lecture2 = new Lecture();
		lecture2.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 17, 0));
		lecture2.setClassroomId("B402");
		lecture2.setTopic("Czech");

		courseService.addLecture(course, lecture1);
		courseService.addLecture(course, lecture2);

		when(courseDao.findByName(any(String.class))).thenReturn(course);

		verify(courseDao, times(2)).update(course);
		Assert.assertEquals(courseService.findByName("testNameCourse").getListOfLectures().size(), 2);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLectureNullCourse() {
		Lecture lecture = new Lecture();
		courseService.addLecture(null, lecture);

		fail("expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLectureNullLecture() {
		Course course = new Course();
		course.setName("testName");
		course.setProficiencyLevel(ProficiencyLevel.A1);
		courseService.addLecture(course, null);

		fail("expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLectureNullCourseNullLecture() {
		courseService.addLecture(null, null);

		fail("expected IllegalArgumentException");
	}

	@Test
	public void testAddLectures() {
		Course course = new Course();
		course.setName("testNameCourse");
		course.setProficiencyLevel(ProficiencyLevel.C1);

		Lecture lecture1 = new Lecture();
		lecture1.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 16, 0));
		lecture1.setClassroomId("B403");
		lecture1.setTopic("English");

		Lecture lecture2 = new Lecture();
		lecture2.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 4, 0));
		lecture2.setClassroomId("B402");
		lecture2.setTopic("Czech");

		Lecture lecture3 = new Lecture();
		lecture3.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 11, 0));
		lecture3.setClassroomId("B401");
		lecture3.setTopic("Poland");

		Set<Lecture> lectures = new HashSet<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		lectures.add(lecture3);

		courseService.addLectures(course, lectures);

		when(courseDao.findByName(any(String.class))).thenReturn(course);

		verify(courseDao, times(1)).update(course);

		Assert.assertEquals(courseService.findByName("testNameCourse").getListOfLectures().size(), 3);
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLecturesNullCourse() {
		Set<Lecture> listOfLectures = new HashSet<>();
		Lecture lecture = new Lecture();
                lecture.setTopic("lecture");
		Lecture lecture2 = new Lecture();
                lecture2.setTopic("lecture2");
		listOfLectures.add(lecture);
		listOfLectures.add(lecture2);
		courseService.addLectures(null, listOfLectures);

		fail("expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLecturesNullLectures() {
		Course course = new Course();
		course.setName("testName");
		course.setProficiencyLevel(ProficiencyLevel.A1);
		courseService.addLectures(course, null);

		fail("expected IllegalArgumentException");
	}

	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testAddLecturesNullCourseNullLectures() {
		courseService.addLectures(null, null);

		fail("expected IllegalArgumentException");
	}

}
