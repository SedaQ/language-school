package com.fi.ls.test.entity;

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
public class LecturerTests {
        
    Lecturer l1;
    Lecturer l2;
    Lecturer l3;
    Language lan1;
    Language lan2;
    Language lan3;
    
    @BeforeClass
    public void BeforeClass()
    {
        l1 = new Lecturer();
        l1.setFirstName("Batman Bin");
        l1.setSurname("Supaman");
        
        l2 = new Lecturer();
        l2.setFirstName("Jesus");
        l2.setSurname("of Nazareth");
        
        l3 = new Lecturer();
        l3.setFirstName("Batman Bin");
        l3.setSurname("Supaman");
    }
    
    @Test
    public void TestEqualsSame()
    {
        Assert.assertTrue(l1.equals(l1));
    }
    
    @Test
    public void TestEquals()
    {
        Assert.assertTrue(l1.equals(l3));
    }
    
    @Test
    public void TestNotEquals()
    {
        Assert.assertTrue(!l1.equals(l2));
    }
    
    @Test
    public void TestNotEqualsNull()
    {
        Assert.assertTrue(!l1.equals(null));
    }
    
    @Test
    public void TestNotEqualsType()
    {
        Assert.assertTrue(!l2.equals("Satan"));
    }
}
