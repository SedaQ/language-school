package com.fi.ls.facade;

import com.fi.ls.dto.LectureDTO;
import com.fi.ls.dto.LecturerDTO;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceException;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LecturerService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Service
@Transactional
public class LecturerFacadeImpl implements LecturerFacade {
    
    @Inject
    private LecturerService lecturerService;
    
    @Inject 
    private BeanMapping beanMapping;
    
    @Override
    public Optional<LecturerDTO> createLecturer(LecturerDTO l) {
        if(l == null)
            throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
        try {
            Lecturer created = lecturerService.create(entity.get());
            return beanMapping.mapTo(created, LecturerDTO.class);
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
            return Optional.empty();
        }
    }

    @Override
    public Optional<LecturerDTO> getLecturerById(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Id parameter is null");
        
        try {
            Lecturer entity = lecturerService.findById(id);
            return beanMapping.mapTo(entity, LecturerDTO.class);
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
            return Optional.empty();
        }
    }

    @Override
    public Optional<LecturerDTO> updateLecturer(LecturerDTO l) {
        if(l == null)
            throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
        try {
            Lecturer updated = lecturerService.update(entity.get());
            return beanMapping.mapTo(updated, LecturerDTO.class);
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
            return Optional.empty();
        }
    }

    @Override
    public void deleteLecturer(LecturerDTO l) {
        if(l == null)
            throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
        try {
            lecturerService.remove(entity.get());
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
        }
    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        try {
            List<Lecturer> entities = lecturerService.findAll();
            return beanMapping.mapTo(entities, LecturerDTO.class);
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
            return null;
        }
    }

	@Override
	public void deleteLecture(LecturerDTO lect, LectureDTO l) {
		if(l == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");
        
        try {
        	lecturerService.deleteLecture(beanMapping.mapTo(lect, Lecturer.class).get(), beanMapping.mapTo(l, Lecture.class).get());
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
        }
		
	}

	@Override
	public void deleteLectures(LecturerDTO lect, List<LectureDTO> l) {
		if(l == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");
        
        try {
        	lecturerService.deleteLectures(beanMapping.mapTo(lect, Lecturer.class).get(), beanMapping.mapTo(l, Lecture.class));
        }
        catch( ServiceException | NoSuchElementException ex) {
            //TODO Log!
        }
		
	}
}
