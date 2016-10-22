package com.fi.ls.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = com.fi.ls.context.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LecturerDaoTest extends AbstractTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private LecturerDao lecturerDao;

	private Lecturer lect;

	@BeforeMethod
	public void init() {
		lect = new Lecturer();
		lect.setFirstName("first name lect1");
		lect.setSurname("surname lect1");

		lecturerDao.create(lect);
	}

	@Test
	public void testCreate() {
		Assert.assertNotNull(lecturerDao.findById(lect.getId()));
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(lecturerDao.findAll().size(), 1);
	}

	@Test
	public void testFindById() {
		Assert.assertEquals(lecturerDao.findById(lect.getId()).getFirstName(), "first name lect1");
		Assert.assertNotEquals(lecturerDao.findById(lect.getId()).getFirstName(), "Some other weird name");
	}

	@Test
	public void testUpdate() {
		lect.setFirstName("TEST update lecturer");
		lecturerDao.update(lect);
		Assert.assertEquals(lecturerDao.findById(lect.getId()).getFirstName(), "TEST update lecturer");
		Assert.assertNotEquals(lecturerDao.findById(lect.getId()).getFirstName(), "first name lect1");
	}

	@Test
	public void testRemove() {
		Assert.assertNotNull(lecturerDao.findById(lect.getId()));
		lecturerDao.remove(lect);
		Assert.assertNull(lecturerDao.findById(lect.getId()));
	}

	@AfterMethod
	public void clearMemory() {
		lect = null;
	}

}
