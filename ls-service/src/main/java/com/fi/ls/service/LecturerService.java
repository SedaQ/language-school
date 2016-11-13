package com.fi.ls.service;

import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceException;
import java.util.List;

/**
 *
 * @author Lukas Daubner (410034)
 */
public interface LecturerService {

    /**
     * create new lecturer in database
     * 
     * @param l
     *            specific Lecturer to be created
     * @return created Lecturer
     * @throws ServiceException
     */
    Lecturer create(Lecturer l) throws ServiceException;
         
    /**
     * Returns all lecturers in language school
     * 
     * @return List of all lecturers which are in language school
     * @throws ServiceException 
     */
    List<Lecturer> findAll() throws ServiceException;
    
    /**
     * finds specific lecturer by id
     * 
     * @param id
     *            id of a Lecturer that would be returned
     * @return specific Lecturer by id
     * @throws ServiceException
     */  
    Lecturer findById(Long id) throws ServiceException;

    /**
     * removes given lecturer from database
     *       
     * @param l
     *            Lecturer that has to be removed
     * @throws ServiceException 
     */
    void remove(Lecturer l) throws ServiceException;
    
    /**
     * updates given lecturer in database
     * 
     * @param l
     *            Lecturer that has to be updated
     * @return updated Lecturer
     * @throws ServiceException
     */
    Lecturer update(Lecturer l) throws ServiceException;
    
}
