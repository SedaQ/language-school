package com.fi.ls.service;

import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.exceptions.ServiceLayerException;
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
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    Lecturer create(Lecturer l);
         
    /**
     * Returns all lecturers in language school
     * 
     * @return List of all lecturers which are in language school
     * @exception ServiceLayerException
     */
    List<Lecturer> findAll();
    
    /**
     * finds specific lecturer by id
     * 
     * @param id
     *            id of a Lecturer that would be returned
     * @return specific Lecturer by id
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */  
    Lecturer findById(Long id);

    /**
     * removes given lecturer from database
     *       
     * @param l
     *            Lecturer that has to be removed
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    void remove(Lecturer l);
    
    /**
     * updates given lecturer in database
     * 
     * @param l
     *            Lecturer that has to be updated
     * @return updated Lecturer
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    Lecturer update(Lecturer l);
    
    /**
     * delete particular lecture
     * 
     * @param l lecture which will be removed
     * @param lect lecturer which remove lecture
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    public void deleteLecture(Lecturer lect, Lecture l);

    /**
     * delete particular lectures
     * 
     * @param l lectures which will be removed
     * @param lect lecturer which remove lectures
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    public void deleteLectures(Lecturer lect, List<Lecture> l);
    
    /**
     * finds all languages of given lecturer
     * 
     * @param l specific lecturer 
     * @return languages of given lecturer
     * @exception ServiceLayerException
     * @exception IllegalArgumentException
     */
    public List<Language> findAllLecturerLanguages(Lecturer l);
}
