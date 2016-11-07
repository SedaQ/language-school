package com.fi.ls.dto;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class LSUserDTO {

	protected Long id;
	private String passwordHash;
	private String email;

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

	public Long getId() {
		return id;
	}

}
