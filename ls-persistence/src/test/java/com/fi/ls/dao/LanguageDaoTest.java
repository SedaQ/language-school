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

	private Lecturer lect1;
	private Lecturer lect2;
	private Language lan1;
	private Language lan2;

	@BeforeMethod
	public void init() {
		lect1 = new Lecturer();
		lect1.setFirstName("first name lect1");
		lect1.setSurname("surname lect1");

		lect2 = new Lecturer();
		lect2.setFirstName("first name lect2");
		lect2.setSurname("surname lect2");

		lan1 = new Language();
		lan1.setLanguage("ENG");
		lan1.setCefrLever(ProficiencyLevel.A1);

		lan2 = new Language();
		lan2.setLanguage("CZE");
		lan2.setCefrLever(ProficiencyLevel.A2);

		lect1.addLanguage(lan1);
		lect2.addLanguage(lan2);

		em.persist(lect1);
		em.persist(lect2);

		languageDao.create(lan1);
		languageDao.create(lan2);
	}

	@Test
	public void testCreate() {
		Assert.assertNotNull(languageDao.findById(lan1.getId()));
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(languageDao.findAll().size(), 2);
	}

	@Test
	public void testFindById() {
		Assert.assertEquals(languageDao.findById(lan1.getId()).getLanguage(), "ENG");
		Assert.assertNotEquals(languageDao.findById(lan1.getId()).getLanguage(), "Some other language");
	}

	@Test
	public void testUpdate() {
		lan1.setLanguage("TEST update language");
		languageDao.update(lan1);
		Assert.assertEquals(languageDao.findById(lan1.getId()).getLanguage(), "TEST update language");
		Assert.assertNotEquals(languageDao.findById(lan1.getId()).getLanguage(), "ENG");
	}

	@Test
	public void testRemove() {
		Assert.assertNotNull(languageDao.findById(lan1.getId()));
		languageDao.remove(lan1);
		Assert.assertNull(languageDao.findById(lan1.getId()));
	}

	@AfterMethod
	public void clearMemory() {
		lect1 = null;
		lect2 = null;
		lan1 = null;
		lan2 = null;
	}

}
