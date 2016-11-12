package com.fi.ls.service;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dao.CourseDao;
import com.fi.ls.entity.Course;
import com.fi.ls.enums.ProficiencyLevel;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
public class CourseServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	@InjectMocks
	private CourseService courseService;

	Course c;

	@BeforeClass
	public void setup() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void init() {
		c = new Course();
		c.setLanguage("eng");
		c.setName("English 101");
		c.setProficiencyLevel(ProficiencyLevel.A1);
		courseService.create(c);
	}

	// @Test
	// public void testCreate() {
	// Assert.assertNotNull(courseService.findById(c.getId()));
	// }
	//
	// @Test
	// public void testUpdate() {
	// c.setLanguage("CZE");
	// Assert.assertEquals(courseService.findById(c.getId()).getLanguage(),
	// "CZE");
	// }
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
