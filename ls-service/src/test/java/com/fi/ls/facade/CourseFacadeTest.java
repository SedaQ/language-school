package com.fi.ls.facade;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.course.CourseCreateDTO;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.CourseService;
import com.fi.ls.service.CourseServiceImpl;
import com.fi.ls.service.LSUserService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourseFacadeTest extends AbstractTestNGSpringContextTests {

	@Mock
	private CourseService courseService;

	@Mock
	private BeanMapping beanMapping;

	private CourseFacade courseFacade;

	private CourseCreateDTO c;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		courseFacade = new CourseFacadeImpl(beanMapping, courseService);
	}

	@BeforeClass
	public void init() {
		c = new CourseCreateDTO();
		c.setLanguage("eng");
		c.setName("English 101");
		c.setProficiencyLevel(ProficiencyLevel.A1);
	}

	@Test
	public void testCreate() {
		//CourseDTO courseId = courseFacade.create(c).get();
		//Assert.assertNotNull(courseFacade.getCourseByName(courseId.getName()));
	}

	//
	// @Test
	// public void getAllCourses() {
	// CourseDTO c1 = new CourseDTO();
	// c1.setLanguage("CZE");
	// c1.setName("Cestina je cool");
	// c1.setProficiencyLevel(ProficiencyLevel.C1);
	// courseFacade.create(c1);
	//
	// Assert.assertEquals(courseFacade.getAllCourses().size(), 2);
	// }
	//

	//
	// @Test
	// public void testFindById() {
	// Assert.assertEquals(courseFacade.getCourseById(c.getId()).get().getLanguage(),
	// "eng");
	// Assert.assertNotEquals(courseFacade.getCourseById(c.getId()).get().getLanguage(),
	// "ENG");
	// }
	//
	// @Test
	// public void testFindByName() {
	// //
	// Assert.assertEquals(courseFacade.getCourseByName(c.getName()).get().getName(),
	// // "English 101");
	// }
	//
	// @Test
	// public void testRemove() {
	// // TODO need implemented this
	// }

	// @Test
	// public void testUpdate() {
	// c.setName("testUpdate");
	// courseFacade.updateCourse(c.getId());
	// Assert.assertEquals(courseFacade.getCourseById(c.getId()).get().getName(),
	// "testUpdate");
	// }

}
