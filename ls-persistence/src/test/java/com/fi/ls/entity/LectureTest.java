package com.fi.ls.entity;

import java.time.LocalDateTime;
import java.time.Month;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Marek Nedbal (357293)
 */
public class LectureTest {
    
    Lecture lecture1;
    Lecture lecture2;
    Lecture lecture3;
    String  lecture4;
    
    @BeforeClass
    public void beforeClass() {
        
        lecture1 = new Lecture();
        lecture2 = new Lecture();
        lecture3 = new Lecture();
        lecture1.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 16, 0));
        lecture2.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 29, 16, 0));
        lecture3.setDayTime(LocalDateTime.of(2016, Month.NOVEMBER, 30, 12, 0));
        lecture1.setClassId("B403");
        lecture2.setClassId("B403");
        lecture3.setClassId("B403");
        lecture1.setTopic("English");
        lecture2.setTopic("English");
        lecture3.setTopic("Farsi");
        lecture4 = "I'm not a lecture!!!";

    } 
    
        @Test
	public void testEqualsSame() {
		Assert.assertTrue(lecture1.equals(lecture1));
	}

	@Test
	public void testEquals() {
		Assert.assertTrue(lecture1.equals(lecture2));
	}

	@Test
	public void testNotEquals() {
		Assert.assertTrue(!lecture1.equals(lecture3));
	}

	@Test
	public void testNotEqualsNull() {
		Assert.assertTrue(!lecture1.equals(null));
	}

	@Test
	public void testNotEqualsType() {
		Assert.assertTrue(!lecture1.equals(lecture4));
	} 
    
}
