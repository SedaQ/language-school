package com.fi.ls.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = com.fi.ls.context.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LSUserRepositoryTest extends AbstractTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public LSUserRepository userDao;

	private LSUser user;

	@BeforeMethod
	public void init() {
		user = new LSUser();
		user.setEmail("test123@email.cz");
		user.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
		userDao.deleteAll();
		Assert.assertEquals(userDao.count(), 0);
	}

	@Test
	public void testCreate() {
		userDao.save(user);
		Assert.assertNotNull(em.find(LSUser.class, user.getId()));
	}

	@Test
	public void testRemove() {
		LSUser userToRemove = new LSUser();
		userToRemove.setEmail("testToRemove@email.cz");
		userToRemove.setPasswordHash("testHashtestHashtestHashtestHasdqewash7841267871s!@$%");
		userDao.save(userToRemove);

		Assert.assertNotNull(userDao.findOne(userToRemove.getId()));
		userDao.delete(userToRemove);
		Assert.assertNull(userDao.findOne(userToRemove.getId()));
	}

	@Test
	public void testUpdate() {
		userDao.save(user);
		user.setEmail("testEmailUpdated@email.cz");
		userDao.save(user);
		Assert.assertEquals(userDao.findOne(user.getId()).getEmail(), "testEmailUpdated@email.cz");
		Assert.assertNotEquals(userDao.findOne(user.getId()).getEmail(), "testFakeEmail@email.cz");
	}

	@Test
	public void testFindById() {
		userDao.save(user);
		Assert.assertEquals(user, userDao.findOne(user.getId()));
	}

	@Test
	public void testFindAll() {
		userDao.save(user);
		LSUser user2 = new LSUser();
		user2.setEmail("test2@email.cz");
		user2.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
		userDao.save(user2);
		Assert.assertEquals(userDao.findAll().size(), 2);
	}

	@Test
	public void testFindByEmail() {
		userDao.save(user);
		Assert.assertNull(userDao.findByEmail("testFAKE@email.cz"));
		Assert.assertNotNull(userDao.findByEmail("test123@email.cz"));
	}

	@AfterMethod
	public void clearMemory() {
		user = null;
	}

}
