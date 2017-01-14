package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.dao.LecturerDao;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.security.UserPasswordEncryption;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Service
public class LecturerServiceImpl implements LecturerService {

	private LecturerDao lecturerDao;
	private LanguageDao languageDao;

	private UserPasswordEncryption userPasswordEncryption;

	@Inject
	public LecturerServiceImpl(LecturerDao lecturerDao, LanguageDao languageDao,
			UserPasswordEncryption userPasswordEncryption) {
		this.lecturerDao = lecturerDao;
		this.languageDao = languageDao;
		this.userPasswordEncryption = userPasswordEncryption;
	}

	@Override
	public Lecturer create(Lecturer l) {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");

		try {
			lecturerDao.create(l);
			return l;
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with creating Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Set<Lecturer> findAll() {
		try {
			return lecturerDao.findAll();
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Lecturer findById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id parameter is null");

		try {
			return lecturerDao.findById(id);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Lecturer, see inner exception.", ex);
		}
	}

	@Override
	// @Transactional
	public void remove(Lecturer l) {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");
		try {
			lecturerDao.findAllLecturerLanguages(l).forEach(lan -> languageDao.remove(lan));
			lecturerDao.remove(l);

		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with removing Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Lecturer update(Lecturer l) {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");

		try {
			return lecturerDao.update(l);
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with updating Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public void deleteLecture(Lecturer lect, Lecture l) {
		if (lect == null || l == null)
			throw new IllegalArgumentException("Lecturer parameter is null or Lecture parameter is null");
		try {
			lect.deleteLecture(l);
			lecturerDao.update(lect);
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with updating Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public void deleteLectures(Lecturer lect, Set<Lecture> l) {
		if (lect == null || l == null)
			throw new IllegalArgumentException("Lecturer parameter is null or Lecture parameter is null");
		try {
			lect.deleteLectures(l);
			lecturerDao.update(lect);
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with updating Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Set<Language> findAllLecturerLanguages(Lecturer l) {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");
		try {
			return lecturerDao.findAllLecturerLanguages(l);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Boolean registerUser(Lecturer u, String unencryptedPassword) {
		if (u == null)
			throw new IllegalArgumentException("LSUser u parameter is null");
		if (unencryptedPassword == null)
			throw new IllegalArgumentException("String unencryptedPassword parameter is null");

		try {
			u.setPasswordHash(userPasswordEncryption.createHash(unencryptedPassword));
			lecturerDao.create(u);
		} catch (RuntimeException ex) {
			throw new ServiceLayerException("Problem with registering Lecturer, see inner exception.", ex);
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean authenticate(Lecturer u, String password) {
		if (u == null)
			throw new IllegalArgumentException("Lecturer u parameter is null");
		if (password == null)
			throw new IllegalArgumentException("String password parameter is null");

		try {
			return userPasswordEncryption.validatePassword(password, u.getPasswordHash());
		} catch (RuntimeException ex) {
			throw new ServiceLayerException("Problem with authenticating Lecturer, see inner exception.", ex);
		}
	}
        
        @Override
	public void addLecture(Lecturer l, Lecture lect) {
		if (lect == null || l == null)
			throw new IllegalArgumentException("Lecture or Lecturer parameter is null");
		l.addLecture(lect);
		lecturerDao.update(l);
	}

}
