package com.fi.ls.dto.user;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class LSUserAuthenticateDTO {

	private Long userId;
	private String password;

	public LSUserAuthenticateDTO() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
