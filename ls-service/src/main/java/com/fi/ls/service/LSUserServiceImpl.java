package com.fi.ls.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.fi.ls.dao.LSUserRepository;
import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class LSUserServiceImpl implements LSUserService {

	@Inject
	private LSUserRepository userDao;

	@Override
	public void create(LSUser c) {
		userDao.save(c);
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

}
