package com.fi.ls.facade;

import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.lecturer.LecturerCreateDTO;
import com.fi.ls.dto.lecturer.LecturerDTO;

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
	 * @return
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
	 * removes given lecturer from database
	 * 
	 * @param l
	 *            lecturer that has to be removed
	 */
	public void deleteLecturer(LecturerDTO l);

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
	 */
	public void deleteLecture(LecturerDTO lect, LectureDTO l);

	/**
	 * delete particular lectures
	 * 
	 * @param l lectures which will be removed
	 * @param lect lecturer which remove lectures
	 */
	public void deleteLectures(LecturerDTO lect, List<LectureDTO> l);
}
