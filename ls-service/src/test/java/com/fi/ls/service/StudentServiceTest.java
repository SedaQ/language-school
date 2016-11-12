/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 *
 * @author Matúš
 */
public class StudentServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private StudentDao studentDao;
    
    @Autowired
    @InjectMocks
    private StudentService studentService;
    /*
    @Autowired
    private LectureDao lectureDao;
    
    Lecture l;
    
    @BeforeMethod
    public void beforeMethod() {
        l = new Lecture();
        l.setDayTime(LocalDateTime.now());
        l.setTopic("Something");
        l.setClassroomId("1");
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullDayTimeNotAllowed () {
        l.setDayTime(null);
        lectureDao.create(l);
        
        Assert.fail("Constraint Violation Exception expected! Null in @NotNull column.");
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullClassroomIdNotAllowed () {
        l.setClassroomId(null);
        lectureDao.create(l);
        
        Assert.fail("Constraint Validation Exception expected! Null in @NotNull column.");
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullTopicNotAllowed () {
        l.setTopic(null);
        lectureDao.create(l);
        
        Assert.fail("Constraint Validation Exception expected! Null in @NotNull column.");
    }
    
    @Test
    public void testFindById() {
        em.persist(l);
        Assert.assertEquals(l, lectureDao.findById(l.getId()));
    }
    
    @Test
    public void testFindAll() {
        Lecture l1 = new Lecture();
        l1.setDayTime(LocalDateTime.of(1990, Month.MARCH, 14, 10, 20));
        l1.setTopic("Random topic");
        l1.setClassroomId("test");
        
        em.persist(l);
        em.persist(l1);
        
        Assert.assertEquals(lectureDao.findAll().size(), 2);
        Assert.assertTrue(lectureDao.findAll().contains(l));
        Assert.assertTrue(lectureDao.findAll().contains(l1));
    }
    
    @Test
    public void testCreateAndRemove() {
        em.persist(l);
        Assert.assertEquals(lectureDao.findAll().size(), 1);
        em.remove(l);
        Assert.assertEquals(lectureDao.findAll().size(), 0);
    }
    
    @Test
    public void testRemoveOtherObject() {
        Lecture l1 = new Lecture();
        l1.setDayTime(LocalDateTime.of(1990, Month.MARCH, 14, 10, 20));
        l1.setTopic("Random topic");
        l1.setClassroomId("test");
        
        em.persist(l);
        Assert.assertEquals(lectureDao.findAll().size(), 1);
        em.remove(l1);
        Assert.assertEquals(lectureDao.findAll().size(), 1);
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void testCreateNull() {
    	lectureDao.create(null);
        
        Assert.fail("Invalid Data Access Api Usage Exception expected! Creating null.");
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveNull() {
    	lectureDao.remove(null);
        
        Assert.fail("Null Pointer Exception expected! Removing null.");
    }
    
    public void testCreateEqualLectures() {   
        Lecture l1 = new Lecture();
        l1.setDayTime(LocalDateTime.now());
        l1.setTopic("Something different");
        l1.setClassroomId("1");
        
        em.persist(l);
        em.persist(l1);
        
        Assert.assertEquals(lectureDao.findAll().size(), 1); 
    }
    
    @Test
    public void testUpdate() {
        em.persist(l);
        l.setTopic("Something else");
        em.persist(l);
        Assert.assertEquals(l.getTopic(), lectureDao.findById(l.getId()).getTopic());
    }
    
    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void testUpdateNull() {
	lectureDao.update(null);
        
        Assert.fail("Invalid Data Access Api Usage Exception expected! Null as paramerer inserted.");           
    }
    */
}
