package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.entity.Language;
import com.fi.ls.exceptions.ServiceException;
import java.util.List;
import java.util.NoSuchElementException;
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
public class LanguageServiceImpl implements LanguageService {
    
    private LanguageDao languageDao;
    
    @Inject
    public LanguageServiceImpl(LanguageDao languageDao){
    	this.languageDao = languageDao;
    }
    
    @Override
    public Language create(Language lan) throws ServiceException {
        if(lan == null)
            throw new IllegalArgumentException("Language parameter is null");
        
        try {
            languageDao.create(lan);
            return lan;
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                ConstraintViolationException |
                JpaSystemException ex) {
            throw new ServiceException("Problem with creating Language, see inner exception.", ex);
        }
    }

    @Override
    public List<Language> findAll() throws ServiceException {
        try {
            return languageDao.findAll();
        }
        catch(  PersistenceException |
                JpaSystemException ex) {
            throw new ServiceException("Problem with finding Language, see inner exception.", ex);
        }
    }

    @Override
    public Language findById(Long id) throws ServiceException {
        if(id == null)
            throw new IllegalArgumentException("Id parameter is null");
        
        try {
            return languageDao.findById(id);
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                JpaSystemException ex) {
            throw new ServiceException("Problem with finding Language, see inner exception.", ex);
        }
    }

    @Override
    public void remove(Language lan) throws ServiceException {
        if(lan == null)
            throw new IllegalArgumentException("Language parameter is null");
        
        try {
            languageDao.remove(lan);
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                JpaSystemException ex) {
            throw new ServiceException("Problem with removing Language, see inner exception.", ex);
        }
    }

    @Override
    public Language update(Language lan) throws ServiceException {
        if(lan == null)
            throw new IllegalArgumentException("Language parameter is null");
        
        try {
            return languageDao.update(lan);
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                ConstraintViolationException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with updating Language, see inner exception.", ex);
        }
    }
}
