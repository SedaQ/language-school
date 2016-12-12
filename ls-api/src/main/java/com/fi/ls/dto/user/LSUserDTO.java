package com.fi.ls.dto.user;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class LSUserDTO {

	protected Long id;
	private String email;
	private String passwordHash;
	private String userRoles;

	public LSUserDTO() {
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		int result = 31 * 1 + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LSUserDTO))
			return false;
		LSUserDTO other = (LSUserDTO) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		return true;
	}

}
