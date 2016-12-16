package com.fi.ls.facade;

import java.util.List;
import java.util.Optional;

import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;

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
	 * @param userId
	 *            user that has to be updated
	 * @return updated user
	 */
	public Optional<LSUserDTO> update(Long userId);

	/**
	 * removes given user
	 * 
	 * @param userId
	 *            user that has to be removed
	 * @return true, if successfully removed
	 */
	public Boolean deleteUser(Long userId);

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
	 * 
	 * @param u
	 * @param unencryptedPassword
	 * @return true, if successful removed
	 */
	public Boolean registerUser(LSUserCreateDTO u, String unencryptedPassword);

	/**
	 * Try to authenticate a user. Return true only if the hashed password
	 * matches the records.
	 * 
	 * @param u
	 * @return true, if successful
	 */
	public Boolean authenticate(LSUserDTO u);

	/**
	 * Check if the given user is admin.
	 */
	public boolean isAdmin(LSUserDTO u);

}
