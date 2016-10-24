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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.entity.Lecturer;

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

	private Lecturer lect1;
	private Lecturer lect2;

	@BeforeMethod
	public void init() {
		lect1 = new Lecturer();
		lect1.setFirstName("first name lect1");
		lect1.setSurname("surname lect1");

		lect2 = new Lecturer();
		lect2.setFirstName("first name lect2");
		lect2.setSurname("surname lect2");

		lecturerDao.create(lect1);
		lecturerDao.create(lect2);
	}

	@Test
	public void testCreate() {
		Assert.assertNotNull(lecturerDao.findById(lect1.getId()));
		Assert.assertNotNull(lecturerDao.findById(lect2.getId()));
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(lecturerDao.findAll().size(), 2);
	}

	@Test
	public void testFindById() {
		Assert.assertEquals(lecturerDao.findById(lect1.getId()).getFirstName(), "first name lect1");
		Assert.assertNotEquals(lecturerDao.findById(lect1.getId()).getFirstName(), "Some other weird name");
	}

	@Test
	public void testUpdate() {
		lect1.setFirstName("TEST update lecturer");
		lecturerDao.update(lect1);
		Assert.assertEquals(lecturerDao.findById(lect1.getId()).getFirstName(), "TEST update lecturer");
		Assert.assertNotEquals(lecturerDao.findById(lect1.getId()).getFirstName(), "first name lect1");
	}

	@Test
	public void testRemove() {
		Assert.assertNotNull(lecturerDao.findById(lect1.getId()));
		lecturerDao.remove(lect1);
		Assert.assertNull(lecturerDao.findById(lect1.getId()));
	}

	@AfterMethod
	public void clearMemory() {
		lect1 = null;
		lect2 = null;
	}

}
