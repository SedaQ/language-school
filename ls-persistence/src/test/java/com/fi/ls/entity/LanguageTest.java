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
public class LanguageTest {

	Lecturer l1;
	Language lan1;
	Language lan2;
	Language lan3;

	@BeforeClass
	public void BeforeClass() {
		l1 = new Lecturer();
		l1.setFirstName("Batman Bin");
		l1.setSurname("Supaman");

		lan1 = new Language();
		lan1.setLanguage("eng");
		lan1.setLecturer(l1);
		lan1.setCefrLever(ProficiencyLevel.C1);

		lan2 = new Language();
		lan2.setLanguage("cmn");
		lan2.setLecturer(l1);
		lan2.setCefrLever(ProficiencyLevel.B2);

		lan3 = new Language();
		lan3.setLanguage("eng");
		lan3.setLecturer(l1);
		lan3.setCefrLever(ProficiencyLevel.C1);

		l1.addLanguage(lan1);
		l1.addLanguage(lan2);
		l1.addLanguage(lan3);
	}

	@Test
	public void TestEqualsSame() {
		Assert.assertTrue(lan1.equals(lan1));
	}

	@Test
	public void TestEquals() {
		Assert.assertTrue(lan1.equals(lan3));
	}

	@Test
	public void TestNotEquals() {
		Assert.assertTrue(!lan1.equals(lan2));
	}

	@Test
	public void TestNotEqualsNull() {
		Assert.assertTrue(!lan1.equals(null));
	}

	@Test
	public void TestNotEqualsType() {
		Assert.assertTrue(!lan1.equals("Yarrr!!!"));
	}

}
