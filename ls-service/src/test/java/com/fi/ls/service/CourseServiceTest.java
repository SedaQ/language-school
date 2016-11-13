package com.fi.ls.service;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;
import com.fi.ls.enums.ProficiencyLevel;
import org.mockito.Mock;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class CourseServiceTest {

	@Mock
	private CourseDao courseDao;

	private CourseService courseService;

	private Course c;

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
	}

	// @Test
	// public void testCreate() {
	// Assert.assertNotNull(courseService.findById(c.getId()));
	// }
        
	@Test
	public void testUpdate() {
	c.setLanguage("CZE");
        
        courseService.update(c);
        
        verify(courseDao, times(1)).update(c);
	}
	//
	// @Test
	// public void testRemove() {
	// Assert.assertNotNull(courseService.findById(c.getId()));
	// courseService.remove(c);
	// Assert.assertNull(courseService.findById(c.getId()));
	// }
	//
	// @Test
	// public void testFindById() {
	// Assert.assertEquals(courseService.findById(c.getId()), c);
	// }
}
