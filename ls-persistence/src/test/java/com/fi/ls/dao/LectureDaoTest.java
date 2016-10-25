package com.fi.ls.dao;

import com.fi.ls.entity.Lecture;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Matus
 */
@ContextConfiguration(classes = com.fi.ls.context.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LectureDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private LectureDao lectureDao;
    
    private Lecture l;
    
    @BeforeMethod
    public void beforeMethod() {
        l = new Lecture();
        l.setDayTime(LocalDateTime.MIN);
        l.setTopic("Something");
    }
    
    @Test(expectedExceptions = ValidationException.class)
    public void nullDayTimeNotAllowed () {
        l.setDayTime(null);
        lectureDao.create(l);
    }
    
    @Test(expectedExceptions = ValidationException.class)
    public void nullTopicNotAllowed () {
        l.setTopic(null);
        lectureDao.create(l);
    }
    //odtialto
    @Test
    public void testFindById() {
        lectureDao.create(l);
        Assert.assertEquals(l, lectureDao.findById(l.getId()));
    }
    
    @Test
    public void testFindAll() {
        Lecture l1 = new Lecture();
        l1.setDayTime(LocalDateTime.MAX);
        l1.setTopic("Random topic");
        
        lectureDao.create(l);
        lectureDao.create(l1);
        
        Assert.assertEquals(lectureDao.findAll().size(), 2);
        Assert.assertTrue(lectureDao.findAll().contains(l));
        Assert.assertTrue(lectureDao.findAll().contains(l1));
    }
    
    @Test
    public void testCreateAndRemove() {
        lectureDao.create(l);
        Assert.assertEquals(lectureDao.findAll().size(), 1);
        lectureDao.remove(l);
        Assert.assertEquals(lectureDao.findAll().size(), 0);
    }
    
    @Test
    public void testUpdate() {
        lectureDao.create(l);
        l.setTopic("Something else");
        lectureDao.update(l);
        Assert.assertEquals(l.getTopic(), lectureDao.findById(l.getId()).getTopic());
    }
    /*
    @Test
    public void testFindById() {
        em.persist(l);
        Assert.assertEquals(l, lectureDao.findById(l.getId()));
    }
    
    @Test
    public void testFindAll() {
        Lecture l1 = new Lecture();
        l1.setDayTime(LocalDateTime.MAX);
        l1.setTopic("Random topic");
        
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
    public void testUpdate() {
        em.persist(l);
        l.setTopic("Something else");
        em.persist(l);
        Assert.assertEquals(l.getTopic(), lectureDao.findById(l.getId()).getTopic());
    }
    */
}
