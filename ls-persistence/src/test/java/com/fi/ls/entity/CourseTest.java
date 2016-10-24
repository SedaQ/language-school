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
public class CourseTest {

	private Course c1;
	private Course c2;
	private Course c3;

	@BeforeClass
	public void beforeClass() {
		c1 = new Course();
		c1.setName("Java Course");

		c2 = new Course();
		c2.setName("Java Course");

		c3 = new Course();
		c3.setName("Java fake Course");
	}

	@Test
	public void testEqualsSame() {
		Assert.assertTrue(c1.equals(c1));
	}

	@Test
	public void testEquals() {
		Assert.assertTrue(c1.equals(c2));
	}

	@Test
	public void testNotEquals() {
		Assert.assertFalse(c1.equals(c3));
	}

	@Test
	public void testNotEqualsNull() {
		Assert.assertFalse(c1.equals(null));
	}

	@Test
	public void testNotEqualsType(){
		Assert.assertFalse(c1.equals("Test String"));
	}

	@AfterClass
	public void clearMemory() {
		c1 = null;
		c2 = null;
		c3 = null;
	}
}
