package com.fi.ls.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Service
public interface LSUserService {
	/**
	 * create new user in database
	 * 
	 * @param c
	 *            specific User to be created
	 */
	public void create(LSUser c);

	/**
	 * finds specific user by id
	 * 
	 * @param id
	 *            of a user that would be returned
	 * @return specific user by id
	 */
	public LSUser findById(Long id);

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
	 * Returns all user in language school
	 * 
	 * @return List of user which are in language school
	 */
	public List<LSUser> findAll();

}
