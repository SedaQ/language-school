package com.fi.ls.facade;

import com.fi.ls.dto.LectureDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Marek Nedbal (357293)
 */
public interface LectureFacade {
    
    /**
     * creates new lecture in database
     * @param lecture to be created
     */
    public void createLecture(LectureDTO lecture);
    
    /**
     * finds a lecture by given id and returns it
     * @param id of lecture to be found
     * @return found lecture
     */
    public Optional<LectureDTO> getLectureById(Long id);
    
    /**
     * returns all lectures in database
     * @return list of all lectures in database
     */
    public List<LectureDTO> getAllLectures();
    
    /**
     * updates given lecture and returns it
     * @param id of lecture to be updated
     * @return updated lecture
     */
    public Optional<LectureDTO> updateLecture(Long id);
    
    /**
     * deletes given lecture
     * @param id of lecture to be deleted
     */
    public void deleteLecture(Long id);
}
