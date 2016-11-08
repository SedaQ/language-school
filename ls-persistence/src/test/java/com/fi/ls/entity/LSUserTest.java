/**
 * 
 */
package com.fi.ls.entity;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class LSUserTest {
	private LSUser u1;
	private LSUser u2;

	@BeforeClass
	public void beforeClass() {
		u1 = new LSUser();
		u1.setEmail("test@email.cz");
		u1.setPasswordHash("asdasdasd123!@!@!@SAKDASKLDAS");

		u2 = new LSUser();
		u2.setEmail("test2@email.cz");
		u2.setPasswordHash("asdasdasd123!@!@!@SAKDASKLDAS");
	}

	@Test
	public void testEqualsSameInstance() {
		Assert.assertTrue(u1.equals(u1));
	}

	@Test
	public void testNotEqualsDifferentEmail() {
		Assert.assertFalse(u1.equals(u2));
	}

	@Test
	public void testNotEqualsNull() {
		Assert.assertFalse(u1.equals(null));
	}

	@Test
	public void testNotEqualsType() {
		Assert.assertFalse(u1.equals("Test String"));
	}

	@AfterClass
	public void clearMemory() {
		u1 = null;
		u2 = null;
	}
}
