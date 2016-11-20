package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import com.fi.ls.dto.language.LanguageDTO;

/**
 *
 * @author Lukas Daubner (410034)
 */
public interface LanguageFacade {
    
	/**
	 * create new lecturers language in database
	 * 
	 * @param lan
	 *            specific lecturers language to be created
	 * @return created language
	 */
	public Optional<LanguageDTO> createLanguage(LanguageDTO lan);

	/**
	 * finds specific lecturers language by id
	 * 
	 * @param id
	 *            id of a lecturers language that would be returned
	 * @return specific lecturers language by id
	 */
	public Optional<LanguageDTO> getLanguageById(Long id);

	/**
	 * updates given lecturers language in database
	 * 
	 * @param lan
	 *            lecturers language that has to be updated
	 * @return updated lecturers language
	 */
	public Optional<LanguageDTO> updateLanguage(LanguageDTO lan);

	/**
	 * removes given lecturers language from database
	 * 
	 * @param lan
	 *            lecturers language that has to be removed
         * @return true, if successfully removed
	 */
	public Boolean deleteLanguage(LanguageDTO lan);

	/**
	 * Returns all lecturers languages in language school
	 * 
	 * @return List of lecturers languages which are in language school
	 */
	public List<LanguageDTO> getAllLanguages();
}
