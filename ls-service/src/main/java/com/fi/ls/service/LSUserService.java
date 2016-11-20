package com.fi.ls.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface LSUserService {

	/**
	 * finds specific user by id
	 * 
	 * @param id
	 *            of a user that would be returned
	 * @return specific user by id
	 */
	public LSUser findById(Long id);

	/**
	 * Returns all user in language school
	 * 
	 * @return List of user which are in language school
	 */
	public List<LSUser> findAll();

	/**
	 * Find specific user by his email
	 * 
	 * @param email
	 *            email to search in String format
	 * @return return specific user by his email
	 */
	public LSUser findByEmail(String email);

	/**
	 * updates given user
	 * 
	 * @param c
	 *            user that has to be updated
	 * @return updated user
	 */
	public LSUser update(LSUser c);

	/**
	 * removes given user
	 * 
	 * @param c
	 *            user that has to be removed
	 */
	public void remove(LSUser c);

	/**
	 * Register the given user with the given unencrypted password.
	 */
	public void registerUser(LSUser u, String unencryptedPassword);

	/**
	 * Try to authenticate a user. Return true only if the hashed password
	 * matches the records.
	 */
	public boolean authenticate(LSUser u, String password);

}
