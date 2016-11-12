package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import com.fi.ls.dto.LSUserDTO;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface LSUserFacade {

	/**
	 * finds specific user by id
	 * 
	 * @param id
	 *            of a user that would be returned
	 * @return specific user by id
	 */
	public Optional<LSUserDTO> getUserById(Long id);

	/**
	 * updates given user
	 * 
	 * @param c
	 *            user that has to be updated
	 * @return updated user
	 */
	public Optional<LSUserDTO> update(Long userId);

	/**
	 * removes given user
	 * 
	 * @param c
	 *            user that has to be removed
	 */
	public void deleteUser(Long userId);

	/**
	 * Returns all courses in language school
	 * 
	 * @return List of courses which are in language school
	 */
	public List<LSUserDTO> getAllUsers();

	/**
	 * Find specific user by his email
	 * 
	 * @param email
	 *            email to search in String format
	 * @return return specific user by his email
	 */
	public Optional<LSUserDTO> getUserByEmail(String email);

	/**
	 * Register the given user with the given unencrypted password.
	 */
	public void registerUser(LSUserDTO u, String unencryptedPassword);

	/**
	 * Try to authenticate a user. Return true only if the hashed password
	 * matches the records.
	 */
	public boolean authenticate(LSUserDTO u);

}
