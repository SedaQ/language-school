package com.fi.ls.facade;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.CourseDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.service.CourseService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourseFacadeTest extends AbstractTestNGSpringContextTests {

	@Inject
	private CourseFacade courseFacade;

	private CourseDTO c;

//	@BeforeMethod
//	public void init() {
//		c = new CourseDTO();
//		c.setLanguage("eng");
//		c.setName("English 101");
//		c.setProficiencyLevel(ProficiencyLevel.A1);
//		courseFacade.create(c);
//	}
//
//	@Test
//	public void getAllCourses() {
//		Course c1 = new Course();
//		c1.setLanguage("CZE");
//		c1.setName("Cestina je cool");
//		c1.setProficiencyLevel(ProficiencyLevel.C1);
//		courseFacade.create(c1);
//
//		Assert.assertEquals(courseFacade.getAllCourses().size(), 2);
//	}
//
//	@Test
//	public void testCreate() {
//		Assert.assertNotNull(courseFacade.getCourseById(c.getId()));
//	}
//
//	@Test
//	public void testUpdate() {
//		c.setName("testUpdate");
//		courseFacade.update(c);
//		Assert.assertEquals(courseFacade.getCourseById(c.getId()).get().getName(), "testUpdate");
//	}
//
//	@Test
//	public void testFindById() {
//		Assert.assertEquals(courseFacade.getCourseById(c.getId()).get().getLanguage(), "eng");
//		Assert.assertNotEquals(courseFacade.getCourseById(c.getId()).get().getLanguage(), "ENG");
//	}

}
