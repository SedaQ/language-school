package com.fi.ls.facade;

import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Lecture;
import com.fi.ls.exceptions.ServiceLayerException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LectureService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marek Nedbal (357293)
 */
@Service
@Transactional
public class LectureFacadeImpl implements LectureFacade {

    private final Logger logger = LoggerFactory.getLogger(LectureFacadeImpl.class);

    private LectureService lectureService;

    private BeanMapping beanMapping;

    @Inject
    public LectureFacadeImpl(LectureService lectureService, BeanMapping beanMapping) {

    	this.lectureService = lectureService;
	this.beanMapping = beanMapping;

    }

    @Override
    public Optional<LectureDTO> createLecture(LectureCreateDTO lecture) {

	if (lecture == null) {

            throw new IllegalArgumentException("Param can not be null!");

	}
	try {
            
			Optional<Lecture> optLect = Optional.ofNullable(lectureService.create(beanMapping.mapTo(lecture, Lecture.class).get()));
			return optLect.isPresent() ? beanMapping.mapTo(optLect.get(), LectureDTO.class) : Optional.empty();

	} catch (NoSuchElementException | ServiceLayerException e) {

            logger.warn("Error occured while creating the LectureDTO!" + e);
            return Optional.empty();

	}

    }

    @Override
    public Optional<LectureDTO> getLectureById(Long id) {

	if (id == null) {

            throw new IllegalArgumentException("Param can not be null!");

	}
	try {

            Lecture lect = lectureService.findById(id);
            return beanMapping.mapTo(lect, LectureDTO.class);

	} catch (NoSuchElementException | ServiceLayerException e) {

            logger.warn("Error occured while searching for Lecture!" + e);
            return Optional.empty();

	}

    }

    @Override
    public List<LectureDTO> getAllLectures() {
            
        try {
            
            List<Lecture> lectures = lectureService.findAll();
            return beanMapping.mapTo(lectures, LectureDTO.class);
                
        } catch (NoSuchElementException | ServiceLayerException e) {
                
            logger.warn("Error occured while searching for all Lectures!" + e);
            return Collections.emptyList();
                
        }
		
    }

    @Override
    public Optional<LectureDTO> updateLecture(LectureDTO lecture) {

	if (lecture == null) {

            throw new IllegalArgumentException("Param can not be null!");

	}
	try {

            Optional<Lecture> optLect = Optional.ofNullable(lectureService.update(beanMapping.mapTo(lecture, Lecture.class).get()));
            return optLect.isPresent() ? beanMapping.mapTo(optLect.get(), LectureDTO.class) : Optional.empty();

        } catch (NoSuchElementException | ServiceLayerException e) {

            logger.warn("Error occured while updating the Lecture!" + e);
            return Optional.empty();

	}

    }

    @Override
    public Boolean deleteLecture(Long id) {

        if (id == null) {

            throw new IllegalArgumentException("Param can not be null!");

	}
	try {

            lectureService.remove(lectureService.findById(id));
            return true;

	} catch (NoSuchElementException | ServiceLayerException e) {

            logger.warn("Error occured while deleting the Lecture!" + e);
            return false;

	}

    }

}
