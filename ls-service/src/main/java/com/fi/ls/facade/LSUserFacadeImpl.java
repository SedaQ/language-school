package com.fi.ls.facade;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.entity.LSUser;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LSUserService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
@Transactional
public class LSUserFacadeImpl implements LSUserFacade {

	private final Logger logger = LoggerFactory.getLogger(LSUserFacadeImpl.class);

	private LSUserService userService;
	private BeanMapping beanMapping;

	@Inject
	public LSUserFacadeImpl(LSUserService userService, BeanMapping beanMapping) {
		this.userService = userService;
		this.beanMapping = beanMapping;
	}

	@Override
	public List<LSUserDTO> getAllUsers() {
		try {
			return beanMapping.mapTo(userService.findAll(), LSUserDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getAllUsers method invokes exception: " + ex);
			return Collections.emptyList();
		}
	}

	@Override
	public Optional<LSUserDTO> getUserById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id parameter is null");
		try {
			Optional<LSUser> user = Optional.of(userService.findById(id));
			return user.isPresent() ? beanMapping.mapTo(user.get(), LSUserDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getUserById method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<LSUserDTO> getUserByEmail(String email) {
		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("email parameter is null or empty");
		try {
			Optional<LSUser> user = Optional.of(userService.findByEmail(email));
			return user.isPresent() ? beanMapping.mapTo(user.get(), LSUserDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getUserByEmail method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<LSUserDTO> update(Long userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId parameter is null in update method");
		try {
			Optional<LSUser> user = Optional.of(userService.findById(userId));
			return user.isPresent() ? beanMapping.mapTo(user.get(), LSUserDTO.class) : Optional.empty();
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("update method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Boolean deleteUser(Long userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId parameter is null in deleteUser method");
		try {
			userService.remove(userService.findById(userId));
			return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("deleteUser method invokes exception: " + ex);
			return true;
		}
	}

	@Override
	public Boolean registerUser(LSUserCreateDTO u, String unencryptedPassword) {
		if (u == null || unencryptedPassword == null || unencryptedPassword.isEmpty())
			throw new IllegalArgumentException(
					"u parameter is null or unencryptedPassword is null or unencryptedPassword is empty in registerUser method");
		try {
			LSUser userEntity = beanMapping.mapTo(u, LSUser.class).get();
			userService.registerUser(userEntity, unencryptedPassword);
			u.setId(userEntity.getId());
			return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("registerUser method invokes exception: " + ex);
			return false;
		}
	}

	@Override
	public Boolean authenticate(LSUserDTO u) {
		if (u == null)
			throw new IllegalArgumentException("LSUserDTO u parametr is null in authenticate method");
		try {
			return userService.authenticate(userService.findById(u.getId()), u.getPasswordHash());
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("authenticate method invokes exception: " + ex);
			return false;
		}
	}
}
