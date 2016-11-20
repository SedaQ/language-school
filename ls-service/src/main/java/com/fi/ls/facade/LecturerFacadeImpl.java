package com.fi.ls.facade;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LecturerService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Service
@Transactional
public class LecturerFacadeImpl implements LecturerFacade {

	private final Logger logger = LoggerFactory.getLogger(LecturerFacadeImpl.class);

	private LecturerService lecturerService;

	private BeanMapping beanMapping;

	@Inject
	public LecturerFacadeImpl(LecturerService lecturerService, BeanMapping beanMapping) {
		this.lecturerService = lecturerService;
		this.beanMapping = beanMapping;
	}

	@Override
	public Optional<LecturerDTO> createLecturer(LecturerCreateDTO l) {
		if (l == null)
			throw new IllegalArgumentException("LecturerDTO parameter is null");

		Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
		try {
			Lecturer created = lecturerService.create(entity.get());
			return beanMapping.mapTo(created, LecturerDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("createLecturer method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<LecturerDTO> getLecturerById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Id parameter is null");

		try {
			Lecturer entity = lecturerService.findById(id);
			return beanMapping.mapTo(entity, LecturerDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getLecturerById method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Optional<LecturerDTO> updateLecturer(LecturerDTO l) {
		if (l == null)
			throw new IllegalArgumentException("LecturerDTO parameter is null");

		Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
		try {
			Lecturer updated = lecturerService.update(entity.get());
			return beanMapping.mapTo(updated, LecturerDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("updateLecturer method invokes exception: " + ex);
			return Optional.empty();
		}
	}

	@Override
	public Boolean deleteLecturer(LecturerDTO l) {
		if (l == null)
			throw new IllegalArgumentException("LecturerDTO parameter is null");

		Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
		try {
			lecturerService.remove(entity.get());
                        return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.debug("deleteLecturer method invokes exception: " + ex);
                        return false;
		}
	}

	@Override
	public List<LecturerDTO> getAllLecturers() {
		try {
			List<Lecturer> entities = lecturerService.findAll();
			return beanMapping.mapTo(entities, LecturerDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("getAllLecturers method invokes exception: " + ex);
			return Collections.emptyList();
		}
	}

	@Override
	public Boolean deleteLecture(LecturerDTO lect, LectureDTO l) {
		if (l == null)
			throw new IllegalArgumentException("LectureDTO parameter is null");
		if (lect == null)
			throw new IllegalArgumentException("LecturerDTO parameter is null");

		try {
			lecturerService.deleteLecture(beanMapping.mapTo(lect, Lecturer.class).get(),
					beanMapping.mapTo(l, Lecture.class).get());
                        return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("deleteLecture method invokes exception: " + ex);
                        return false;
		}

	}

	@Override
	public Boolean deleteLectures(LecturerDTO lect, List<LectureDTO> l) {
		if (l == null)
			throw new IllegalArgumentException("List<LectureDTO> parameter is null");
		if (lect == null)
			throw new IllegalArgumentException("LecturerDTO parameter is null");

		try {
			lecturerService.deleteLectures(beanMapping.mapTo(lect, Lecturer.class).get(),
					beanMapping.mapTo(l, Lecture.class));
                        return true;
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("deleteLectures method invokes exception: " + ex);
                        return false;
		}

	}

        @Override
        public List<LanguageDTO> findAllLecturerLanguages(LecturerDTO l) {
                if (l == null)
                    throw new IllegalArgumentException("LectureDTO parameter is null");
            
		try {
                        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
			List<Language> entities = lecturerService.findAllLecturerLanguages(entity.get());
			return beanMapping.mapTo(entities, LanguageDTO.class);
		} catch (ServiceLayerException | NoSuchElementException ex) {
			logger.warn("findAllLecturerLanguages method invokes exception: " + ex);
			return Collections.emptyList();
		}
        }
}
