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
public class LanguageDaoTest extends AbstractTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private LanguageDao languageDao;

	Lecturer lect1;
	Language lan1;

	@BeforeMethod
	public void init() {
		lan1 = new Language();
		lan1.setLanguage("ENG");
		lan1.setProficiencyLevel(ProficiencyLevel.A1);

		lect1 = new Lecturer();
		lect1.setNickname("nickname lect1");
		lect1.setFirstName("first name lect1");
		lect1.setSurname("surname lect1");
		lect1.addLanguage(lan1);

		em.persist(lect1);
	}

	@Test
	public void testCreate() {
		languageDao.create(lan1);
		Assert.assertNotNull(em.find(Language.class, lan1.getId()));
	}

	@Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void testCreateNull() {
		languageDao.create(null);
		Assert.fail(
				"Language object cannot be null. Exception org.springframework.dao.InvalidDataAccessApiUsageException.class expected");
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullLanguage() {
		lan1.setLanguage(null);
		languageDao.create(lan1);
		Assert.fail(
				"Language cannot be setted to null. Exception javax.validation.ConstraintViolationException.class expected");
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullLecturer() {
		lan1.setLecturer(null);
		languageDao.create(lan1);
		Assert.fail(
				"Lecturer cannot be setted to null. Exception javax.validation.ConstraintViolationException.class expected");
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullProficiencyLevel() {
		lan1.setProficiencyLevel(null);
		languageDao.create(lan1);
		Assert.fail(
				"ProficiencyLevel cannot be setted to null. Exception javax.validation.ConstraintViolationException.class expected.");
	}

	@Test
	public void testFindById() {
		em.persist(lan1);
		Assert.assertEquals(languageDao.findById(lan1.getId()).getLanguage(), "ENG");
		Assert.assertNotEquals(languageDao.findById(lan1.getId()).getLanguage(), "Some other language");
	}

	@Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void testFindByIdNull() {
		em.persist(lan1);
		languageDao.findById(null);
		Assert.fail(
				"I cannot call function findById with null parameter. Exception org.springframework.dao.InvalidDataAccessApiUsageException expected");
	}

	@Test
	public void testUpdate() {
		em.persist(lan1);
		lan1.setLanguage("TEST update language");
		languageDao.update(lan1);
		Assert.assertEquals(languageDao.findById(lan1.getId()).getLanguage(), "TEST update language");
		Assert.assertNotEquals(languageDao.findById(lan1.getId()).getLanguage(), "ENG");
	}

	@Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
	public void testUpdateNull() {
		em.persist(lan1);
		languageDao.update(null);
		Assert.fail(
				"Update function cannot be call with null parameter. Exception org.springframework.dao.InvalidDataAccessApiUsageException.class expected.");
	}

	@Test
	public void testFindAll() {
		Lecturer lect2 = new Lecturer();
		lect2.setNickname("nickname lect2");
		lect2.setFirstName("first name lect2");
		lect2.setSurname("surname lect2");

		Language lan2 = new Language();
		lan2.setLanguage("CZE");
		lan2.setProficiencyLevel(ProficiencyLevel.A2);
		lect2.addLanguage(lan2);

		em.persist(lect1);
		em.persist(lect2);
		languageDao.create(lan2);
		languageDao.create(lan1);

		Assert.assertEquals(languageDao.findAll().size(), 2);
	}

	@Test
	public void testRemove() {
		em.persist(lan1);
		Assert.assertNotNull(languageDao.findById(lan1.getId()));
		languageDao.remove(lan1);
		Assert.assertNull(languageDao.findById(lan1.getId()));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void testRemoveNull() {
		em.persist(lan1);
		languageDao.remove(null);
		Assert.fail(
				"Remove function cannot be called with null parameter. Exception NullPointerException.class expected.");
	}

	@AfterMethod
	public void clearMemory() {
		lect1 = null;
		lan1 = null;
	}

}
