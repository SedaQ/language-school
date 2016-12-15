package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.dto.lecture.LectureDTO;

/**
 *
 * @author Marek Nedbal (357293)
 */
public interface LectureFacade {
    
    /**
     * creates new lecture in database
     * @param lecture to be created
     * @return created lecture
     */
    public Optional<LectureDTO> createLecture(LectureCreateDTO lecture);
    
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
     * @param lecture lecture to be updated
     * @return updated lecture
     */
    public Optional<LectureDTO> updateLecture(LectureDTO lecture);
    
    /**
     * deletes given lecture
     * @param id of lecture to be deleted
     * @return true, if successfully removed
     */
    public Boolean deleteLecture(Long id);
}
