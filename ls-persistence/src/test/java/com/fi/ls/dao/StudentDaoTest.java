package com.fi.ls.dao;

import com.fi.ls.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
 * @author Marek Nedbal (357293)
 */
@ContextConfiguration(classes = com.fi.ls.context.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StudentDaoTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StudentDao studentDao;

    Student student1;
        
    @BeforeMethod
    public void init() {
            
        student1 = new Student();
        student1.setFirstName("Teysa");
        student1.setSurname("Karlov");
        student1.setBirthNumber("846013/5821");
            
    }
        
    @Test
    public void testCreate() {
            
        studentDao.create(student1);
        Assert.assertNotNull(em.find(Student.class, student1.getId())); 
    }
        
    @Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testCreateNullParam() {

        studentDao.create(null);
        Assert.fail("NULL insetred as parameter. Exception expected!");
                
    }
        
    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreateStudentNullName() {
            
        student1.setFirstName(null);
        studentDao.create(student1);
        Assert.fail("Student with NULL name inserted. Constraint did not work.");
            
    }
       
    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreateStudentNullSurname() {
            
        student1.setSurname(null);
        studentDao.create(student1);
        Assert.fail("Student with NULL surname inserted. Constraint did not work.");
            
    }
    
    @Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
    public void testCreateStudentBirthNumberDupl() {
        
        Student student2 = new Student();
        student2.setFirstName("Clonium");
        student2.setSurname("Maximo");
        student2.setBirthNumber("846013/5821");
        studentDao.create(student1);
        studentDao.create(student2);
        Assert.fail("Student with non-unique birth number inserted. Constraint did not work.");
            
    }    
        
    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testCreateStudentNullBirthNumber() {
            
        student1.setBirthNumber(null);
        studentDao.create(student1);
        Assert.fail("Student with NULL birth number inserted. Constraint did not work.");
            
    }
        
    @Test
    public void testFindById() {
            
        em.persist(student1);
        Assert.assertEquals(studentDao.findById(student1.getId()).getBirthNumber(), "846013/5821");
                
    }

    @Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindByIdNull() {

        studentDao.findById(null);
        Assert.fail("NULL param inserted. Exception expected!");
                
    }

    @Test
    public void testUpdate() {
        
	em.persist(student1);
	student1.setSurname("Envoy of Ghosts");
	studentDao.update(student1);
	Assert.assertEquals(studentDao.findById(student1.getId()).getSurname(), "Envoy of Ghosts");

    }

    @Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testUpdateNull() {

	studentDao.update(null);
        Assert.fail("NULL param inserted. Exception expected!");
               
	}

    @Test
    public void testFindAll() {
        
	Student student2 = new Student();
        student2.setFirstName("Pia");
        student2.setSurname("Nalaar");
        student2.setBirthNumber("765820/7414");
	em.persist(student1);
	em.persist(student2);
	Assert.assertEquals(studentDao.findAll().size(), 2);
        
	}

    @Test
    public void testRemove() {
        
	em.persist(student1);
	Assert.assertNotNull(studentDao.findById(student1.getId()));
	studentDao.remove(student1);
	Assert.assertNull(studentDao.findById(student1.getId()));
        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveNull() {

    	studentDao.remove(null);
        Assert.fail("NULL param inserted. NullPointerException expected!");
                
    }
    
}