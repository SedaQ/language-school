package com.fi.ls.service;

import com.fi.ls.dto.LanguageDTO;
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
     *            specific LanguageDTO to be created
     * @return created LanguageDTO
     * @throws ServiceException
     */
    LanguageDTO create(LanguageDTO lan) throws ServiceException;
         
    /**
     * Returns all lecturers languages in language school
     * 
     * @return List of all lecturers languages which are in language school
     * @throws ServiceException 
     */
    List<LanguageDTO> findAll() throws ServiceException;
    
    /**
     * finds specific lecturers language by id
     * 
     * @param id
     *            id of a LanguageDTO that would be returned
     * @return specific LanguageDTO by id
     * @throws ServiceException
     */  
    LanguageDTO findById(Long id) throws ServiceException;

    /**
     * removes given lecturers language from database
     *       
     * @param lan
     *            LanguageDTO that has to be removed
     * @throws ServiceException 
     */
    void remove(LanguageDTO lan) throws ServiceException;
    
    /**
     * updates given lecturers language in database
     * 
     * @param lan
     *            LanguageDTO that has to be updated
     * @return updated lecturerDTO
     * @throws ServiceException
     */
    LanguageDTO update(LanguageDTO lan) throws ServiceException;
    
}
