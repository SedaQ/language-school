package com.fi.ls.service;

import com.fi.ls.security.UserPasswordEncryption;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fi.ls.dao.LSUserRepository;
import com.fi.ls.entity.LSUser;
import com.fi.ls.exceptions.ServiceLayerException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public class LSUserServiceImpl implements LSUserService {

	private LSUserRepository userDao;
        
    private UserPasswordEncryption userPasswordEncryption;
	
	@Inject
	public LSUserServiceImpl(LSUserRepository userDao, UserPasswordEncryption userPasswordEncryption){
		this.userDao = userDao;
                this.userPasswordEncryption = userPasswordEncryption;
	}

	@Override
	public void registerUser(LSUser u, String unencryptedPassword) {
            if (u == null)
                throw new IllegalArgumentException("LSUser u parameter is null");
            if (unencryptedPassword == null)
                throw new IllegalArgumentException("String unencryptedPassword parameter is null");
            
            try {
                u.setPasswordHash(userPasswordEncryption.createHash(unencryptedPassword));
                userDao.save(u);
            }
            catch(RuntimeException ex) {
                throw new ServiceLayerException("Problem with registering LS-User, see inner exception.", ex);
            }
	}

	@Override
	public boolean authenticate(LSUser u, String password) {
            if (u == null)
                throw new IllegalArgumentException("LSUser u parameter is null");
            if (password == null)
                throw new IllegalArgumentException("String password parameter is null");
            
            try {
                return userPasswordEncryption.validatePassword(password, u.getPasswordHash());
            }
            catch(RuntimeException ex) {
                throw new ServiceLayerException("Problem with authenticating LS-User, see inner exception.", ex);
            }
	}

	@Override
	public LSUser findById(Long id) {
            if (id == null)
                throw new IllegalArgumentException("Long id parameter is null");
            
            try {
                return userDao.findOne(id);
            }
            catch(DataAccessException | ConstraintViolationException ex) {
                throw new ServiceLayerException("Problem with finding LS-User, see inner exception.", ex);
            }
	}

	@Override
	public LSUser update(LSUser c) {
            if (c == null)
                throw new IllegalArgumentException("LSUser c parameter is null");
            
            try {
                return userDao.save(c);
            }
            catch(DataAccessException | ConstraintViolationException ex) {
                throw new ServiceLayerException("Problem with updating LS-User, see inner exception.", ex);
            }
	}

	@Override
	public void remove(LSUser c) {
            if (c == null)
                throw new IllegalArgumentException("LSUser c parameter is null");
            
            try {
                userDao.delete(c);
            }
            catch(DataAccessException | ConstraintViolationException ex) {
                throw new ServiceLayerException("Problem with deleting LS-User, see inner exception.", ex);
            }
	}

	@Override
	public List<LSUser> findAll() {
            try {
		return userDao.findAll();
            }
            catch(DataAccessException | ConstraintViolationException ex) {
                throw new ServiceLayerException("Problem with finding LS-User, see inner exception.", ex);
            }
	}

	@Override
	public LSUser findByEmail(String email) {
            if (email == null)
                throw new IllegalArgumentException("String email parameter is null");
            
            try {
                return userDao.findByEmail(email);
            }
            catch(DataAccessException | PersistenceException | ConstraintViolationException ex) {
                throw new ServiceLayerException("Problem with finding LS-User, see inner exception.", ex);
            }
	}

}
