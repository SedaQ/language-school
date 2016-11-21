package com.fi.ls.security;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class UserPasswordEncryptionTest {

	private UserPasswordEncryption userPwdEncryption;

	private String password;
	private String passwordHash;

	@BeforeClass()
	public void init() {
		password = "testPassword";
		userPwdEncryption = new UserPasswordEncryption();
	}

	@Test
	public void testCreateHash() {
		passwordHash = userPwdEncryption.createHash(password);
		Assert.assertNotEquals(password, passwordHash);
	}

	@Test
	public void testValidatePassword() {
		Assert.assertTrue(userPwdEncryption.validatePassword(password, passwordHash));
	}

}
