package com.fi.ls.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface LSUserRepository extends JpaRepository<LSUser, Long> {

	/**
	 * Find specific user by his email
	 * 
	 * @param email
	 *            email to search in String format
	 * @return return specific user by his email
	 */
	@Query("SELECT u FROM #{#entityName} u WHERE u.email=:email")
	public LSUser findByEmail(@Param("email") String email);

}
