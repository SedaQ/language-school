package com.fi.ls.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fi.ls.dao.LSUserRepository;
import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class LSUserServiceImpl implements LSUserService {

	private LSUserRepository userDao;
	
	@Inject
	public LSUserServiceImpl(LSUserRepository userDao){
		this.userDao = userDao;
	}

	@Override
	public void registerUser(LSUser u, String unencryptedPassword) {
		u.setPasswordHash(UserPasswordEncryption.createHash(unencryptedPassword));
		userDao.save(u);
	}

	@Override
	public boolean authenticate(LSUser u, String password) {
		return UserPasswordEncryption.validatePassword(password, u.getPasswordHash());
	}

	@Override
	public LSUser findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public LSUser update(LSUser c) {
		return userDao.save(c);
	}

	@Override
	public void remove(LSUser c) {
		userDao.delete(c);
	}

	@Override
	public List<LSUser> findAll() {
		return userDao.findAll();
	}

	@Override
	public LSUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
