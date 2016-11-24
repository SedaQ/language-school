package com.fi.ls.facade;

import javax.transaction.Transactional;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.entity.LSUser;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LSUserService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LSUserFacadeTest extends AbstractTestNGSpringContextTests {

	@Mock
	private LSUserService userService;

	@Mock
	private BeanMapping beanMapping;

	private LSUserFacade userFacade;

	private LSUserDTO user1;
	private LSUserCreateDTO user2;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
		doReturn(Optional.of(new LSUser())).when(beanMapping).mapTo(any(LSUserDTO.class), eq(LSUser.class));
		doReturn(Optional.of(new LSUser())).when(beanMapping).mapTo(any(LSUserCreateDTO.class), eq(LSUser.class));
		doReturn(Optional.of(new LSUserDTO())).when(beanMapping).mapTo(any(LSUser.class), eq(LSUserDTO.class));

		userFacade = new LSUserFacadeImpl(userService, beanMapping);
	}

	@BeforeMethod
	public void beforeMethod() {
		user1 = new LSUserDTO();
		user2 = new LSUserCreateDTO();
	}

	@AfterMethod
	public void afterMethod() {
		reset(userService);
	}

	@Test
	public void testGetAllUsers() {
		userFacade.getAllUsers();
		verify(userService, times(1)).findAll();
	}

	@Test
	public void testGetUserById() {
		userFacade.getUserById(Long.MAX_VALUE);
		verify(userService, times(1)).findById(any(Long.class));
	}

	@Test
	public void testGetUserByEmail() {
		userFacade.getUserByEmail("testEmail@seznam.cz");
		verify(userService, times(1)).findByEmail(any(String.class));
	}

	@Test
	public void testUpdate() {
		LSUserDTO userDTO = new LSUserDTO();
		userDTO.setId(Long.MAX_VALUE);
		LSUser user = beanMapping.mapTo(userDTO, LSUser.class).get();

		when(userService.findById(Long.MAX_VALUE)).thenReturn(user);

		userFacade.update(Long.MAX_VALUE);
		verify(userService, times(1)).update(user);
	}

	@Test
	public void testDeleteUser() {
		LSUserDTO userDTO = new LSUserDTO();
		userDTO.setId(Long.MAX_VALUE);
		LSUser user = beanMapping.mapTo(userDTO, LSUser.class).get();

		userFacade.deleteUser(Long.MAX_VALUE);
		verify(userService, times(1)).update(user);
	}

	@Test
	public void testRegisterUser() {
		userFacade.registerUser(user2, "testPassword!@!@D123123");
		verify(userService, times(1)).registerUser(any(LSUser.class), any(String.class));
	}

	@Test
	public void testAuthenticate() {
		userFacade.authenticate(user1);
		verify(userService, times(1)).authenticate(any(LSUser.class), any(String.class));
	}
}
