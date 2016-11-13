package com.fi.ls.service;

import com.fi.ls.dao.LecturerDao;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Service
public class LecturerServiceImpl implements LecturerService {

	@Inject
	private LecturerDao lecturerDao;

	@Override
	public Lecturer create(Lecturer l) throws ServiceException {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");

		try {
			lecturerDao.create(l);
			return l;
		} catch (PersistenceException | InvalidDataAccessApiUsageException | ConstraintViolationException
				| JpaSystemException ex) {
			throw new ServiceException("Problem with creating Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public List<Lecturer> findAll() throws ServiceException {
		try {
			return lecturerDao.findAll();
		} catch (PersistenceException | JpaSystemException ex) {
			throw new ServiceException("Problem with finding Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Lecturer findById(Long id) throws ServiceException {
		if (id == null)
			throw new IllegalArgumentException("Id parameter is null");

		try {
			return lecturerDao.findById(id);
		} catch (PersistenceException | InvalidDataAccessApiUsageException | JpaSystemException ex) {
			throw new ServiceException("Problem with finding Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public void remove(Lecturer l) throws ServiceException {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");

		try {
			lecturerDao.remove(l);
		} catch (PersistenceException | InvalidDataAccessApiUsageException | JpaSystemException ex) {
			throw new ServiceException("Problem with removing Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public Lecturer update(Lecturer l) throws ServiceException {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");

		try {
			return lecturerDao.update(l);
		} catch (PersistenceException | InvalidDataAccessApiUsageException | ConstraintViolationException
				| JpaSystemException ex) {
			throw new ServiceException("Problem with updating Lecturer, see inner exception.", ex);
		}
	}

	@Override
	public void deleteLecture(Lecturer lect, Lecture l) throws ServiceException {
		if (lect == null || l == null)
			throw new IllegalArgumentException("Lecturer parameter is null or Lecture parameter is null");
		lect.deleteLecture(l);
	}

	@Override
	public void deleteLectures(Lecturer lect, List<Lecture> l) throws ServiceException {
		if (lect == null || l == null)
			throw new IllegalArgumentException("Lecturer parameter is null or Lecture parameter is null");
		lect.deleteLectures(l);
	}
}
