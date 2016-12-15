package com.fi.ls.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fi.ls.enums.UserRoles;

/**
 * @author Pavel Šeda (441048)
 *
 *         Parent User entity for login to system (uses pattern for email)
 */
@Entity
@Table(name = "lsuser")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "LSUser.findAll", query = "SELECT u FROM LSUser u")
public class LSUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	protected Long id;

	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(unique = true)
	private String email;

	@NotNull
	@Column(name = "password")
	private String passwordHash;

	@Column(name = "user_role")
	private String userRole;

	public LSUser() {
	}

	public Long getId() {
		return id;
	}

        public void setId(Long id) {
            this.id = id;
        }

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LSUser))
			return false;
		LSUser other = (LSUser) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LSUser [id=" + id + ", email=" + email + ", passwordHash=" + passwordHash + "]";
	}

}
