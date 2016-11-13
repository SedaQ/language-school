package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.entity.LSUser;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LSUserService;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
@Transactional
public class LSUserFacadeImpl implements LSUserFacade {

	@Inject
	private LSUserService userService;

	@Inject
	private BeanMapping beanMapping;

	@Override
	public List<LSUserDTO> getAllUsers() {
		return beanMapping.mapTo(userService.findAll(), LSUserDTO.class);
	}

	@Override
	public Optional<LSUserDTO> getUserById(Long id) {
		Optional<LSUser> user = Optional.of(userService.findById(id));
		return user.isPresent() ? beanMapping.mapTo(user.get(), LSUserDTO.class) : Optional.empty();
	}

	@Override
	public Optional<LSUserDTO> getUserByEmail(String email) {
		Optional<LSUser> user = Optional.of(userService.findByEmail(email));
		return user.isPresent() ? beanMapping.mapTo(user.get(), LSUserDTO.class) : Optional.empty();
	}

	@Override
	public Optional<LSUserDTO> update(Long userId) {
		Optional<LSUser> user = Optional.of(userService.findById(userId));
		return user.isPresent() ? beanMapping.mapTo(user.get(), LSUserDTO.class) : Optional.empty();
	}

	@Override
	public void deleteUser(Long userId) {
		userService.remove(userService.findById(userId));
	}

	@Override
	public void registerUser(LSUserCreateDTO u, String unencryptedPassword) {
		LSUser userEntity = beanMapping.mapTo(u, LSUser.class).get();
		userService.registerUser(userEntity, unencryptedPassword);
		u.setId(userEntity.getId());
	}

	@Override
	public boolean authenticate(LSUserDTO u) {
		return userService.authenticate(userService.findById(u.getId()), u.getPasswordHash());
	}

}
