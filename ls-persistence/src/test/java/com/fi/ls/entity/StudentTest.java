package com.fi.ls.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Matúš
 */
public class StudentTest {
    
    Student s1;
    Student s2;
    Student s3;

    @BeforeClass
    public void beforeClass() {
	s1 = new Student();
        s1.setFirstName("Jim");
        s1.setSurname("Carry");
        s1.setBirthNumber("8811229999");

	s2 = new Student();
        s2.setFirstName("Ozzy");
        s2.setSurname("Osbourne");
        s2.setBirthNumber("42");
        
	s3 = new Student();
        s3.setFirstName("Jim");
        s3.setSurname("Carry");
        s3.setBirthNumber("8811229999");
    }
    
    @Test
    public void testEqualsSame() {
	Assert.assertTrue(s1.equals(s1));
    }

    @Test
    public void testEquals() {
    	Assert.assertTrue(s1.equals(s3));
    }

    @Test
    public void testNotEquals() {
    	Assert.assertFalse(s1.equals(s2));
    }

    @Test
    public void testNotEqualsNull() {
    	Assert.assertFalse(s1.equals(null));
    }

    @Test
    public void testNotEqualsType() {
    	Assert.assertFalse(s2.equals("String"));
    }
}