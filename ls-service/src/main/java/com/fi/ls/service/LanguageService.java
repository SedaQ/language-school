package com.fi.ls.service;

import com.fi.ls.entity.Language;
import com.fi.ls.exceptions.ServiceException;
import java.util.List;

/**
 *
 * @author Lukas Daubner (410034)
 */
public interface LanguageService {

    /**
     * create new lecturers language in database
     * 
     * @param lan
     *            specific Language to be created
     * @return created Language
     * @throws ServiceException
     */
    Language create(Language lan) throws ServiceException;
         
    /**
     * Returns all lecturers languages in language school
     * 
     * @return List of all lecturers languages which are in language school
     * @throws ServiceException 
     */
    List<Language> findAll() throws ServiceException;
    
    /**
     * finds specific lecturers language by id
     * 
     * @param id
     *            id of a Language that would be returned
     * @return specific Language by id
     * @throws ServiceException
     */  
    Language findById(Long id) throws ServiceException;

    /**
     * removes given lecturers language from database
     *       
     * @param lan
     *            Language that has to be removed
     * @throws ServiceException 
     */
    void remove(Language lan) throws ServiceException;
    
    /**
     * updates given lecturers language in database
     * 
     * @param lan
     *            Language that has to be updated
     * @return updated Language
     * @throws ServiceException
     */
    Language update(Language lan) throws ServiceException;
    
}
