package com.fi.ls.dao;

import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.entity.Student;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ValidationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Matúš
 */
@ContextConfiguration(classes = com.fi.ls.context.PersistenceApplicationContext.class)
public class LectureDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    Lecture l1 = new Lecture();
    Lecture l2 = new Lecture();
    Lecture l3 = new Lecture();
    Student s1 = new Student();
    Student s2 = new Student();
    Lecturer l = new Lecturer();
    Course c = new Course();
    
    @BeforeClass
    public void beforeClass() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        l1.addCourse(c);
        l2.addCourse(c);
        
        l1.addLecturer(l);
        l2.addLecturer(l);
        
        l1.addStudent(s1);
        l1.addStudent(s2);
        l2.addStudent(s1);
        
        l1.setDayTime(LocalDateTime.MIN);
        l2.setDayTime(LocalDateTime.MIN);
        
        l1.setTopic("Random talk");
        l2.setTopic("Random talk");
        
        em.persist(l1);
        em.persist(l2);
        
        em.getTransaction().commit();
        em.close();
    }
    /**
    @Test(expectedExceptions = ValidationException.class)
    public void nullIntoNotNullColumn() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(l3);
        
        //sem by sa to nemalo dostat - len na commit do branchu
        em.getTransaction().commit();
        em.close();
    }*/
    @Test
    public void lectureHasStudents() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Lecture tl = em.find(Lecture.class, l1.getId());
        Assert.assertEquals(tl.getListOfStudents().size(), 2);
        
        em.getTransaction().commit();
        em.close();
    }
}
