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
import org.testng.annotations.BeforeClass;
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

	private Lecturer lect;
	private Language lan;

	@BeforeMethod
	public void init() {
		lect = new Lecturer();
		lect.setFirstName("first name lect1");
		lect.setSurname("surname lect1");

		lan = new Language();
		lan.setLanguage("ENG");
		lan.setCefrLever(ProficiencyLevel.A1);

		lect.addLanguage(lan);
		em.persist(lect);
		languageDao.create(lan);
	}

	@Test
	public void testCreate() {
		Assert.assertNotNull(languageDao.findById(lan.getId()));
	}

	@Test
	public void testFindById() {
		Assert.assertEquals(languageDao.findById(lan.getId()).getLanguage(), "ENG");
		Assert.assertNotEquals(languageDao.findById(lan.getId()).getLanguage(), "Some other language");
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(languageDao.findAll().size(), 1);
	}

	@Test
	public void testUpdate() {
		lan.setLanguage("TEST update language");
		languageDao.update(lan);
		Assert.assertEquals(languageDao.findById(lan.getId()).getLanguage(), "TEST update language");
		Assert.assertNotEquals(languageDao.findById(lan.getId()).getLanguage(), "ENG");
	}

	@Test
	public void testRemove() {
		Assert.assertNotNull(languageDao.findById(lan.getId()));
		languageDao.remove(lan);
		Assert.assertNull(languageDao.findById(lan.getId()));
	}

	@AfterMethod
	public void clearMemory() {
		lect = null;
		lan = null;
	}

}
