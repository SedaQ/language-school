package com.fi.ls.service;

import com.fi.ls.dao.LectureDao;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Marek Nedbal (357293)
 */
public class LectureServiceImpl implements LectureService {

	private LectureDao lectureDao;

	@Inject
	public LectureServiceImpl(LectureDao lectureDao) {
		this.lectureDao = lectureDao;
	}

	@Override
	public Lecture create(Lecture lecture) {
		if (lecture == null)
			throw new IllegalArgumentException("Lecture parameter is null");
		try {
			lectureDao.create(lecture);
			return lecture;
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with creating Lecture, see inner exception.", ex);
		}

	}

	@Override
	public Lecture findById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Lecture id parameter is null");
		try {
			return lectureDao.findById(id);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Lecture, see inner exception.", ex);
		}
	}

	@Override
	public Lecture update(Lecture lecture) {
		if (lecture == null)
			throw new IllegalArgumentException("Lecture lecture parameter is null");
		try {
			return lectureDao.update(lecture);
		} catch (PersistenceException | ConstraintViolationException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with updating Lecture, see inner exception.", ex);
		}

	}

	@Override
	public void remove(Lecture lecture) {
		if (lecture == null)
			throw new IllegalArgumentException("Lecture lecture parameter is null");
		try {
			lectureDao.remove(lecture);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with removing Lecture, see inner exception.", ex);
		}
	}

	@Override
	public List<Lecture> findAll() {
		try {
			return lectureDao.findAll();
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Lecture, see inner exception.", ex);
		}
	}

}
