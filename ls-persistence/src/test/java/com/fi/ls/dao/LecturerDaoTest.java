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

	Lecturer lect1;

	@BeforeMethod
	public void init() {
		lect1 = new Lecturer();
		lect1.setNickname("nickname lect1");
		lect1.setFirstName("first name lect1");
		lect1.setSurname("surname lect1");
	}

	@Test
	public void testCreate() {
		lecturerDao.create(lect1);
		Assert.assertNotNull(em.find(Lecturer.class, lect1.getId()));
	}

	@Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void testCreateNull() {
		lecturerDao.create(null);
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullName() {
		lect1.setFirstName(null);
		lecturerDao.create(lect1);
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullSurname() {
		lect1.setSurname(null);
		lecturerDao.create(lect1);
	}

	@Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
	public void testCreateLecturerNickNameDupl() {
		Lecturer lecturer2 = new Lecturer();
		lecturer2.setNickname("nickname lect1"); // nick name should be unique
		lecturer2.setFirstName("first name lecturer2");
		lecturer2.setSurname("surname lect1");
		lecturerDao.create(lect1);
		lecturerDao.create(lecturer2);
	}

	@Test
	public void testFindById() {
		em.persist(lect1);
		Assert.assertEquals(lecturerDao.findById(lect1.getId()).getNickname(), "nickname lect1");
		Assert.assertNotEquals(lecturerDao.findById(lect1.getId()).getNickname(), "Some other weird name");
	}

	@Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void testFindByIdNull() {
		em.persist(lect1);
		lecturerDao.findById(null);
	}

	@Test
	public void testUpdate() {
		em.persist(lect1);
		lect1.setNickname("TEST update lecturer");
		lecturerDao.update(lect1);
		Assert.assertEquals(em.find(Lecturer.class, lect1.getId()).getNickname(), "TEST update lecturer");
		Assert.assertNotEquals(em.find(Lecturer.class, lect1.getId()).getNickname(), "nickname lect1");
	}

	@Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void testUpdateNull() {
		em.persist(lect1);
		lecturerDao.update(null);
	}

	@Test
	public void testFindAll() {
		Lecturer lect2 = new Lecturer();
		lect2.setNickname("nickname lect2");
		lect2.setFirstName("first name lect2");
		lect2.setSurname("surname lect2");

		em.persist(lect1);
		em.persist(lect2);
		Assert.assertEquals(lecturerDao.findAll().size(), 2);
	}

	@Test
	public void testRemove() {
		em.persist(lect1);
		Assert.assertNotNull(lecturerDao.findById(lect1.getId()));
		lecturerDao.remove(lect1);
		Assert.assertNull(lecturerDao.findById(lect1.getId()));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void testRemoveNull() {
		em.persist(lect1);
		lecturerDao.remove(null);
	}

	@AfterMethod
	public void clearMemory() {
		lect1 = null;
	}

}
