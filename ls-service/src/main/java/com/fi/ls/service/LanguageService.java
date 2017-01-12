package com.fi.ls.service;

import com.fi.ls.entity.Language;
import com.fi.ls.exceptions.ServiceLayerException;
import java.util.List;
import java.util.Set;

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
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    Language create(Language lan);
         
    /**
     * Returns all lecturers languages in language school
     * 
     * @return List of all lecturers languages which are in language school 
     * @exception ServiceLayerException
     */
    Set<Language> findAll();
    
    /**
     * finds specific lecturers language by id
     * 
     * @param id
     *            id of a Language that would be returned
     * @return specific Language by id
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */  
    Language findById(Long id);

    /**
     * removes given lecturers language from database
     *       
     * @param lan
     *            Language that has to be removed
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    void remove(Language lan);
    
    /**
     * updates given lecturers language in database
     * 
     * @param lan
     *            Language that has to be updated
     * @return updated Language
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    Language update(Language lan);
    
}
