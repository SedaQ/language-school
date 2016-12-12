package com.fi.ls.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fi.ls.entity.UserRoles;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

	/**
	 * Find specific user by user_id
	 * 
	 * @param user_id
	 *            user_id to search specific User
	 * @return return specific userRole by user_id
	 */
	@Query("SELECT u FROM #{#entityName} u WHERE u.user_id=:user_id")
	public UserRoles findByUserId(@Param("user_id") String user_id);

}
