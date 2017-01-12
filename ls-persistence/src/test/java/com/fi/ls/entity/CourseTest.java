package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fi.ls.enums.ProficiencyLevel;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pavel Šeda (441048)
 *
 */
public class CourseTest {

	private Course c1;
	private Course c2;
	private Course c3;
	private Course c4;
	private Course c5;

	@BeforeClass
	public void beforeClass() {
		c1 = new Course();
		c1.setName("Java Course");
		c1.setLanguage("CZK");
		c1.setProficiencyLevel(ProficiencyLevel.C2);

		c2 = new Course();
		c2.setName("Java Course");
		c2.setLanguage("ENG");
		c2.setProficiencyLevel(ProficiencyLevel.C2);

		c3 = new Course();
		c3.setName("Java Course");
		c3.setLanguage("CZK");
		c3.setProficiencyLevel(ProficiencyLevel.A1);

		c4 = new Course();
		c4.setName("Java fake Course");
		c4.setLanguage("CZK");
		c4.setProficiencyLevel(ProficiencyLevel.A1);

		c5 = new Course();
		c5.setName("Java Course");
		c5.setLanguage("CZK");
		c5.setProficiencyLevel(ProficiencyLevel.C2);
	}
	
	@Test
	public void testAddListOfLectures() {
		Set<Lecture> lectures = new HashSet<>();
                Lecture l1 = new Lecture();
                l1.setTopic("l1");
                Lecture l2 = new Lecture();
                l2.setTopic("l2");
                Lecture l3 = new Lecture();
                l3.setTopic("l3");
		lectures.add(l1);
		lectures.add(l2);
		lectures.add(l3);
		c1.addLectures(lectures);
		Assert.assertEquals(c1.getListOfLectures().size(), 3);
	}

	@Test
	public void testEqualsSameInstance() {
		Assert.assertTrue(c1.equals(c1));
	}

	@Test
	public void testEqualsSameAttributes() {
		Assert.assertTrue(c1.equals(c5));
	}

	@Test
	public void testNotEqualsDifferentName() {
		Assert.assertFalse(c1.equals(c4));
	}

	@Test
	public void testNotEqualsDifferentLanguage() {
		Assert.assertFalse(c1.equals(c2));
	}

	@Test
	public void testNotEqualsDifferentProficiencyLevel() {
		Assert.assertFalse(c1.equals(c3));
	}

	@Test
	public void testNotEqualsNull() {
		Assert.assertFalse(c1.equals(null));
	}

	@Test
	public void testNotEqualsType() {
		Assert.assertFalse(c1.equals("Test String"));
	}

	@AfterClass
	public void clearMemory() {
		c1 = null;
		c2 = null;
		c3 = null;
		c4 = null;
		c5 = null;
	}
}
