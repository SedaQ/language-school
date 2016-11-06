/**
 * 
 */
package com.fi.ls.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fi.ls.entity.LSUser;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface UserDao extends JpaRepository<LSUser, Long> {

}
