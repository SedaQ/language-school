/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.service;

import com.fi.ls.dto.LecturerDTO;
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
     *            specific LecturerDTO to be created
     * @return created LecturerDTO
     * @throws ServiceException
     */
    LecturerDTO create(LecturerDTO l) throws ServiceException;
         
    /**
     * Returns all lecturers in language school
     * 
     * @return List of all lecturers which are in language school
     * @throws ServiceException 
     */
    List<LecturerDTO> findAll() throws ServiceException;
    
    /**
     * finds specific lecturer by id
     * 
     * @param id
     *            id of a LecturerDTO that would be returned
     * @return specific LecturerDTO by id
     * @throws ServiceException
     */  
    LecturerDTO findById(Long id) throws ServiceException;

    /**
     * removes given lecturer from database
     *       
     * @param l
     *            LecturerDTO that has to be removed
     * @throws ServiceException 
     */
    void remove(LecturerDTO l) throws ServiceException;
    
    /**
     * updates given lecturer in database
     * 
     * @param l
     *            lecturerDTO that has to be updated
     * @return updated lecturerDTO
     * @throws ServiceException
     */
    LecturerDTO update(LecturerDTO l) throws ServiceException;
    
}
