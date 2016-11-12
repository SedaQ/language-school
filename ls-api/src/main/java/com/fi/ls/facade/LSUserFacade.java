package com.fi.ls.facade;

import java.util.List;

import com.fi.ls.dto.LSUserDTO;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface LSUserFacade {

	/**
	 * create new user in database
	 * 
	 * @param c
	 *            specific User to be created
	 */
	public void create(LSUserDTO c);

	/**
	 * finds specific user by id
	 * 
	 * @param id
	 *            of a user that would be returned
	 * @return specific user by id
	 */
	public LSUserDTO findById(Long id);

	/**
	 * updates given user
	 * 
	 * @param c
	 *            user that has to be updated
	 * @return updated user
	 */
	public LSUserDTO update(LSUserDTO c);

	/**
	 * removes given user
	 * 
	 * @param c
	 *            user that has to be removed
	 */
	public void remove(LSUserDTO c);

	/**
	 * Returns all courses in language school
	 * 
	 * @return List of courses which are in language school
	 */
	public List<LSUserDTO> findAll();

}
