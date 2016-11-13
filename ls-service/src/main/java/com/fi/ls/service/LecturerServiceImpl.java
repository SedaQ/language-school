package com.fi.ls.service;

import com.fi.ls.dao.LecturerDao;
import com.fi.ls.dto.LecturerDTO;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceException;
import com.fi.ls.mapping.BeanMapping;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class LecturerServiceImpl implements LecturerService {
    
    @Inject
    private LecturerDao lecturerDao;
    
    @Inject 
    private BeanMapping beanMapping;
    
    @Override
    public LecturerDTO create(LecturerDTO l) throws ServiceException {
        if(l == null)
            throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
        try {
            lecturerDao.create(entity.get());
            Optional<LecturerDTO> dto = beanMapping.mapTo(entity, LecturerDTO.class);
            return dto.get();
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                ConstraintViolationException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with creating Lecturer, see inner exception.", ex);
        }
    }

    @Override
    public LecturerDTO findById(Long id) throws ServiceException {
        if(id == null)
            throw new IllegalArgumentException("Id parameter is null");
        
        try {
            Lecturer entity = lecturerDao.findById(id);
            Optional<LecturerDTO> dto = beanMapping.mapTo(entity, LecturerDTO.class);
            return dto.get();
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with finding Lecturer, see inner exception.", ex);
        }
    }

    @Override
    public LecturerDTO update(LecturerDTO l) throws ServiceException {
        if(l == null)
            throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
        try {
            lecturerDao.update(entity.get());
            Optional<LecturerDTO> dto = beanMapping.mapTo(entity, LecturerDTO.class);
            return dto.get();
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                ConstraintViolationException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with updating Lecturer, see inner exception.", ex);
        }
    }

    @Override
    public void remove(LecturerDTO l) throws ServiceException {
        if(l == null)
            throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Optional<Lecturer> entity = beanMapping.mapTo(l, Lecturer.class);
        try {
            lecturerDao.remove(entity.get());
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with removing Lecturer, see inner exception.", ex);
        }
    }

    @Override
    public List<LecturerDTO> findAll() throws ServiceException {
        try {
            List<Lecturer> entities = lecturerDao.findAll();
            return beanMapping.mapTo(entities, LecturerDTO.class);
        }
        catch(  PersistenceException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with finding Lecturer, see inner exception.", ex);
        }
    }
}
