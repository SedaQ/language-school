package com.fi.ls.entity;

import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.enums.ProficiencyLevel;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class LecturerTest {

	Lecturer l1;
	Lecturer l2;
	Lecturer l3;
	Language lan1;
	Language lan2;
	Language lan3;

	@BeforeClass
	public void beforeClass() {
		l1 = new Lecturer();
		l1.setNickname("Superhero");
		l1.setFirstName("Batman Bin");
		l1.setSurname("Supaman");
		l1.setEmail("Superhero@test.cz");
		l1.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

		l2 = new Lecturer();
		l2.setNickname("Christ");
		l2.setFirstName("Jesus");
		l2.setSurname("of Nazareth");
		l2.setEmail("Christ@test.cz");
		l2.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");

		l3 = new Lecturer();
		l3.setNickname("Superhero");
		l3.setFirstName("Batman Bin");
		l3.setSurname("Supaman");
		l3.setEmail("Superhero@test.cz");
		l3.setPasswordHash("testHashtestHashtestHashtestHash7841267871s!@$%");
	}

	@Test
	public void testEqualsSame() {
		Assert.assertTrue(l1.equals(l1));
	}

	@Test
	public void testEquals() {
		Assert.assertTrue(l1.equals(l3));
	}

	@Test
	public void testNotEquals() {
		Assert.assertTrue(!l1.equals(l2));
	}

	@Test
	public void testNotEqualsNull() {
		Assert.assertTrue(!l1.equals(null));
	}

	@Test
	public void testNotEqualsType() {
		Assert.assertTrue(!l2.equals("Satan"));
	}
}
