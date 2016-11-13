package com.fi.ls.facade;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.user.LSUserDTO;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

	@Inject
	private LSUserFacade userFacade;

	private LSUserDTO user1;

//	@BeforeClass
//	public void init() {
//		user1 = new LSUserDTO();
//		user1.setEmail("testemail@email.cz");
//		userFacade.registerUser(user1, "Password123!");
//	}
//
//	@Test
//	public void testRegisterUser() {
//		Assert.assertNotNull(userFacade.getUserById(user1.getId()));
//	}
//
//	@Test
//	public void testPasswordHash() {
//		Assert.assertNotEquals(userFacade.getUserById(user1.getId()).get().getPasswordHash(), "Password123!");
//	}
//
//	@Test
//	public void testAuthenticateUser() {
//		// Assert.assertTrue(userFacade.authenticate(userFacade.getUserById(user1.getId()).get()));
//	}
//
//	@Test
//	public void testGetAllUsers() {
//		LSUserDTO user2 = new LSUserDTO();
//		user2.setEmail("testemail2231@seznam.cz");
//		userFacade.registerUser(user2, "password2");
//
//		Assert.assertEquals(userFacade.getAllUsers().size(), 2);
//	}
//
//	@Test
//	public void testGetUserById() {
//		Assert.assertNotNull(userFacade.getUserById(user1.getId()).get().getEmail(), "testemail@email.cz");
//	}
//
//	@Test
//	public void testGetUserByEmail() {
//		Assert.assertEquals(userFacade.getUserByEmail(user1.getEmail()).get().getId(), user1.getId());
//	}

//	@Test
//	public void testUpdate() {
//		user1.setEmail("changedEmail@seznam.cz");
//		userFacade.update(userFacade.getUserByEmail(user1.getEmail()).get().getId());
//		Assert.assertEquals(userFacade.getUserByEmail(user1.getEmail()).get().getEmail(), "changedEmail@seznam.cz");
//	}

	// @Test
	// public void testDeleteUser() {
	// LSUserDTO userToRemove = new LSUserDTO();
	// userToRemove.setEmail("userToRemove@seznam.cz");
	// userToRemove.setPasswordHash("userPasswordFake");
	// userFacade.registerUser(userToRemove, "testPassword");
	//
	// Assert.assertNotNull(userFacade.getUserByEmail("userToRemove@seznam.cz"));
	// userFacade.deleteUser(userToRemove.getId());
	// Assert.assertNull(userFacade.getUserById(userToRemove.getId()));
	// }
}
