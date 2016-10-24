package com.fi.ls.entity;

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
    String  lecture3;
    
    @BeforeClass
    public void beforeClass() {
        
        lecture1 = new Lecture();
        lecture2 = new Lecture();
        lecture3 = "I'm not a lecture!!!";

    } 
    
    @Test
    public void testEqualsSame() {
        
	Assert.assertTrue(lecture1.equals(lecture1));
        
    }

    @Test
    public void testNotEqualsNull() {
        
        Assert.assertTrue(!lecture1.equals(null));
        
    }

    @Test
    public void testNotEqualsType() {
        
        Assert.assertTrue(!lecture1.equals(lecture3));
        
    }    
    
}
