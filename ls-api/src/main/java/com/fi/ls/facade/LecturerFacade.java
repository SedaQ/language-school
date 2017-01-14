package com.fi.ls.facade;

import com.fi.ls.dto.language.LanguageDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;
import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Lukas Daubner (410034)
 */
public interface LecturerFacade {

	/**
	 * create new lecturer in database
	 * 
	 * @param l
	 *            specific Lecturer to be created
	 * @return created lecturer
	 */
	public Optional<LecturerDTO> createLecturer(LecturerCreateDTO l);

	/**
	 * finds specific lecturer by id
	 * 
	 * @param id
	 *            id of a lecturer that would be returned
	 * @return specific Lecturer by id
	 */
	public Optional<LecturerDTO> getLecturerById(Long id);

	/**
	 * updates given lecturer in database
	 * 
	 * @param l
	 *            lecturer that has to be updated
	 * @return updated lecturer
	 */
	public Optional<LecturerDTO> updateLecturer(LecturerDTO l);

	/**
	 * removes given lecturer and its languages from database
	 * 
	 * @param l
	 *            lecturer that has to be removed
         * @return true, if successfully removed
	 */
	public Boolean deleteLecturer(LecturerDTO l);

	/**
	 * Returns all lecturers in language school
	 * 
	 * @return List of lecturers which are in language school
	 */
	public List<LecturerDTO> getAllLecturers();

	/**
	 * delete particular lecture
	 * 
	 * @param l lecture which will be removed
	 * @param lect lecturer which remove lecture
         * @return true, if successfully removed
	 */
	public Boolean deleteLecture(LecturerDTO lect, LectureDTO l);

	/**
	 * delete particular lectures
	 * 
	 * @param l lectures which will be removed
	 * @param lect lecturer which remove lectures
         * @return true, if successfully removed
	 */
	public Boolean deleteLectures(LecturerDTO lect, List<LectureDTO> l);
        
        /**
         * finds all languages of given lecturer
         * 
         * @param l specific lecturer 
         * @return languages of given lecturer
         */
        public List<LanguageDTO> findAllLecturerLanguages(LecturerDTO l);

        /**
	 * add lecture to lecturer
	 * @param lect lecture to add
         * @param l lecturer to which lecture will be added
         * @return true, if successfully added
         */
        public Boolean addLecture(LecturerDTO l, LectureDTO lect);
    
	/**
	 * Register the given user with the given unencrypted password.
	 * 
	 * @param u
	 * @param unencryptedPassword
	 * @return true, if successful removed
	 */
	public Boolean registerUser(LecturerDTO u, String unencryptedPassword);

	/**
	 * Try to authenticate a user. Return true only if the hashed password
	 * matches the records.
	 * 
	 * @param u
	 * @return true, if successful
	 */
	public Boolean authenticate(LecturerDTO u);
}
