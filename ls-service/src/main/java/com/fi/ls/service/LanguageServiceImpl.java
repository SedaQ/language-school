/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.service;

import com.fi.ls.dao.LanguageDao;
import com.fi.ls.dto.LanguageDTO;
import com.fi.ls.entity.Language;
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
 * @author lukas
 */
public class LanguageServiceImpl implements LanguageService {
    
    @Inject
    private LanguageDao languageDao;
    
    @Inject 
    private BeanMapping beanMapping;

    @Override
    public LanguageDTO create(LanguageDTO lan) throws ServiceException {
        if(lan == null)
            throw new IllegalArgumentException("LanguageDTO parameter is null");
        
        Optional<Language> entity = beanMapping.mapTo(lan, Language.class);
        try {
            languageDao.create(entity.get());
            Optional<LanguageDTO> dto = beanMapping.mapTo(entity, LanguageDTO.class);
            return dto.get();
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                ConstraintViolationException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with creating Language, see inner exception.", ex);
        }
    }

    @Override
    public List<LanguageDTO> findAll() throws ServiceException {
        try {
            List<Language> entities = languageDao.findAll();
            return beanMapping.mapTo(entities, LanguageDTO.class);
        }
        catch(  PersistenceException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with finding Language, see inner exception.", ex);
        }
    }

    @Override
    public LanguageDTO findById(Long id) throws ServiceException {
        if(id == null)
            throw new IllegalArgumentException("Id parameter is null");
        
        try {
            Language entity = languageDao.findById(id);
            Optional<LanguageDTO> dto = beanMapping.mapTo(entity, LanguageDTO.class);
            return dto.get();
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with finding Language, see inner exception.", ex);
        }
    }

    @Override
    public void remove(LanguageDTO lan) throws ServiceException {
        if(lan == null)
            throw new IllegalArgumentException("LanguageDTO parameter is null");
        
        Optional<Language> entity = beanMapping.mapTo(lan, Language.class);
        try {
            languageDao.remove(entity.get());
        }
        catch(  PersistenceException |
                InvalidDataAccessApiUsageException |
                JpaSystemException |
                NoSuchElementException ex) {
            throw new ServiceException("Problem with removing Language, see inner exception.", ex);
        }
    }

    @Override
    public LanguageDTO update(LanguageDTO lan) throws ServiceException {
        if(lan == null)
            throw new IllegalArgumentException("LanguageDTO parameter is null");
        
        Optional<Language> entity = beanMapping.mapTo(lan, Language.class);
        try {
            languageDao.update(entity.get());
            Optional<LanguageDTO> dto = beanMapping.mapTo(entity, LanguageDTO.class);
            return dto.get();
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
