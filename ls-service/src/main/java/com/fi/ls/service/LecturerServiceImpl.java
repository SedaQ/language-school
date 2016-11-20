package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.dao.LecturerDao;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceLayerException;
import java.util.List;
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

	@Inject
	public LecturerServiceImpl(LecturerDao lecturerDao, LanguageDao languageDao) {
		this.lecturerDao = lecturerDao;
                this.languageDao = languageDao;
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
	public List<Lecturer> findAll() {
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
        @Transactional
	public void remove(Lecturer l) {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");
		try {
                        for(Language lan : lecturerDao.findAllLecturerLanguages(l)) {
                            languageDao.remove(lan);
                        }
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
		lect.deleteLecture(l);
		lecturerDao.update(lect);
	}

	@Override
	public void deleteLectures(Lecturer lect, List<Lecture> l) {
		if (lect == null || l == null)
			throw new IllegalArgumentException("Lecturer parameter is null or Lecture parameter is null");
		lect.deleteLectures(l);
		lecturerDao.update(lect);
	}

        @Override
        public List<Language> findAllLecturerLanguages(Lecturer l) {
		if (l == null)
			throw new IllegalArgumentException("Lecturer parameter is null");
		try {
			return lecturerDao.findAllLecturerLanguages(l);
		} catch (PersistenceException | DataAccessException ex) {
			throw new ServiceLayerException("Problem with finding Lecturer, see inner exception.", ex);
		}
        }
}
