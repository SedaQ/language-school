package com.fi.ls.dao;

import com.fi.ls.entity.Language;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        Language lan1;
        Language lan2;

	@BeforeMethod
	public void init() {
		lect1 = new Lecturer();
		lect1.setNickname("nickname lect1");
		lect1.setFirstName("first name lect1");
		lect1.setSurname("surname lect1");
		lect1.setEmail("test3@email.cz");
		lect1.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
                
                lan1 = new Language();
                lan1.setLanguage("Lang-1");
                lan1.setProficiencyLevel(ProficiencyLevel.C1);
                
                lan2 = new Language();
                lan2.setLanguage("Lang-2");
                lan2.setProficiencyLevel(ProficiencyLevel.A2);
                
                lect1.addLanguage(lan1);
                lect1.addLanguage(lan2);
	}

	@Test
	public void testCreate() {
		lecturerDao.create(lect1);
		Assert.assertNotNull(em.find(Lecturer.class, lect1.getId()));
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void testCreateNull() {
		lecturerDao.create(null);
		Assert.fail(
				"Lecturer object cannot null. Exception org.springframework.dao.InvalidDataAccessApiUsageException.class expected.");
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullName() {
		lect1.setFirstName(null);
		lecturerDao.create(lect1);
		Assert.fail(
				"Lecturer cannot be created with firstName null parameter. Exception javax.validation.ConstraintViolationException.class expected.");
	}

	@Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
	public void testCreateNullSurname() {
		lect1.setSurname(null);
		lecturerDao.create(lect1);
		Assert.fail(
				"Lecture cannot be created with surname null parameter. Exception javax.validation.ConstraintViolationException.class.");
	}

	@Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
	public void testCreateLecturerNickNameDupl() {
		Lecturer lecturer2 = new Lecturer();
		lecturer2.setNickname("nickname lect1"); // nick name should be unique
		lecturer2.setFirstName("first name lecturer2");
		lecturer2.setSurname("surname lect1");
		lecturer2.setEmail("test4@email.cz");
		lecturer2.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
		lecturerDao.create(lect1);
		lecturerDao.create(lecturer2);
		Assert.fail(
				"Lecturers with same nickName cannot exist. Exception org.springframework.orm.jpa.JpaSystemException.class expected.");
	}

	@Test
	public void testFindById() {
		em.persist(lect1);
		Assert.assertEquals(lecturerDao.findById(lect1.getId()).getNickname(), "nickname lect1");
		Assert.assertNotEquals(lecturerDao.findById(lect1.getId()).getNickname(), "Some other weird name");
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void testFindByIdNull() {
		em.persist(lect1);
		lecturerDao.findById(null);
		Assert.fail(
				"I cannot call function findById with null parameter. Exception org.springframework.dao.InvalidDataAccessApiUsageException expected");
	}

	@Test
	public void testUpdate() {
		em.persist(lect1);
		lect1.setNickname("TEST update lecturer");
		lecturerDao.update(lect1);
		Assert.assertEquals(em.find(Lecturer.class, lect1.getId()).getNickname(), "TEST update lecturer");
		Assert.assertNotEquals(em.find(Lecturer.class, lect1.getId()).getNickname(), "nickname lect1");
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void testUpdateNull() {
		em.persist(lect1);
		lecturerDao.update(null);
		Assert.fail(
				"Update function cannot be call with null parameter. Exception org.springframework.dao.InvalidDataAccessApiUsageException.class expected.");
	}

	@Test
	public void testFindAll() {
		Lecturer lect2 = new Lecturer();
		lect2.setNickname("nickname lect2");
		lect2.setFirstName("first name lect2");
		lect2.setSurname("surname lect2");
		lect2.setEmail("test5@email.cz");
		lect2.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

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
		Assert.fail(
				"Remove function cannot be called with null parameter. Exception NullPointerException.class expected.");
	}
        
        @Test
        public void testFindAllLecturerLanguages() {
            em.persist(lect1);
            em.persist(lan1);
            em.persist(lan2);
            
            Set<Language> langs = lecturerDao.findAllLecturerLanguages(lect1);
            
            Assert.assertEquals(langs.size(), 2);
            
        }
        
        @Test(expectedExceptions = NullPointerException.class)
        public void testFindAllLecturerLanguagesNull() {
            
            lecturerDao.findAllLecturerLanguages(null);
            Assert.fail(
                    "Find all lecturer languages function cannot be called with null parameter. Exception NullPointerException.class expected.");
            
        }

	@AfterMethod
	public void clearMemory() {
		lect1 = null;
	}

}
