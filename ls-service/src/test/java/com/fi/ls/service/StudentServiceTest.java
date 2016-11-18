package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Student;
import com.fi.ls.exceptions.ServiceLayerException;
import java.util.List;
import javax.persistence.PersistenceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 *
 * @author Matúš
 */
public class StudentServiceTest {
    
    @Mock
    private StudentDao studentDao;
    
    private StudentService studentService;
    
    Student s1;
    Student s2;
    Student s3;
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(studentDao);
    }
    
    @BeforeMethod
    public void beforeMethod() {
        s1 = new Student();
        s2 = new Student();
        s3 = new Student();
        
        s1.setBirthNumber("123456789");
        s2.setBirthNumber("234567891");
        s3.setBirthNumber("1234567890");
        
        s1.setEmail("enterprise.java1@project.com");
        s2.setEmail("enterprise.java2@project.com");
        s3.setEmail("enterprise.java3@project.com");
        
        s1.setPasswordHash("hashTest123456");
        s2.setPasswordHash("hashTest456789");
        s3.setPasswordHash("hashTest987456");
        
        s1.setFirstName("enterprise");
        s2.setFirstName("enterprise");
        s3.setFirstName("enterprise3");
        
        s1.setSurname("java1");
        s2.setSurname("java");
        s3.setSurname("java");
    }

    @Test
    public void testCreate(){
        studentService.create(s1);
        verify(studentDao, times(1)).create(s1);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull(){
        studentService.create(null);
        fail("Null has been created.");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testCreateServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).create(any(Student.class));
        studentService.create(s1);
        fail("Expected service layer exception in create.");
    }
    
    @Test
    public void testRemove(){
        studentService.remove(s1);
        verify(studentDao, times(1)).remove(s1);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveNull(){
        studentService.remove(null);
        fail("Null has been removed.");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testRemoveServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).remove(any(Student.class));
        studentService.remove(s1);
        fail("Expected service layer exception in update.");
    }
    
    @Test
    public void testUpdate(){
        s1.setBirthNumber("123");
        studentService.update(s1);
        verify(studentDao, times(1)).update(s1);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testNullUpdate(){
        studentService.update(null);
        fail("Null has been updated.");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testUpdateServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).update(any(Student.class));
        s1.setBirthNumber("123");
        studentService.update(s1);
        fail("Expected service layer exception in update.");
    }
    
    @Test
    public void testFindByBirthNumber(){
        studentService.findByBirthNumber("number");
        verify(studentDao, times(1)).findByBirthNumber("number");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByBirthNumberNull(){
        studentService.findByBirthNumber(null);
        fail("Found by null birth number.");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindByBirthNumberServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).findByBirthNumber(any(String.class));
        studentService.findByBirthNumber("number");
        fail("Expected service layer exception in find by birth number.");
    }
    
    @Test
    public void testFindById(){
        studentService.findById(Long.MIN_VALUE);
        verify(studentDao, times(1)).findById(Long.MIN_VALUE);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByIdNull(){
        studentService.findById(null);
        fail("Found by null id.");
    }
            
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindByIdServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).findById(any(Long.class));
        studentService.findById(Long.MIN_VALUE);
        fail("Expected service layer exception in find by id.");
    }
    
    @Test
    public void testFindAllStudents(){
        studentService.findAllStudents();
        verify(studentDao, times(1)).findAll();
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindAllStudentsServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).findAll();
        studentService.findAllStudents();
        fail("Expected service layer exception in find by id.");
    }
    
    @Test
    public void testFindByFirstName(){
        studentService.create(s1);
        studentService.create(s2);
        studentService.create(s3);
        List<Student> s = studentService.findByFirstName("enterprise");
        assertEquals(s.size(), 2);
    }
    
    @Test
    public void testFindByFirstName2(){
        studentService.findByFirstName("test");
        verify(studentDao, times(1)).findByFirstName("test");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByFirstNameNull(){
        studentService.findByFirstName(null);
        fail("Found by null first name.");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindByFirstNameServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).findByFirstName(any(String.class));
        studentService.findByFirstName("test");
        fail("Expected service layer exception in find by surname.");
    }
    
    @Test
    public void testFindBySurname(){
        studentService.create(s1);
        studentService.create(s2);
        studentService.create(s3);
        List<Student> s = studentService.findBySurname("java");
        assertEquals(s.size(), 2);
    }
    
    @Test
    public void testFindBySurname2(){
        studentService.findBySurname("test");
        verify(studentDao, times(1)).findBySurname("test");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindBySurnameNull(){
        studentService.findBySurname(null);
        fail("Found by null first name.");
    }
    
    @Test(expectedExceptions = {ServiceLayerException.class})
    public void testFindBySurnameServiceException(){
        doThrow(new PersistenceException("")).when(studentDao).findBySurname(any(String.class));
        studentService.findBySurname("test");
        fail("Expected service layer exception in find by surname.");
    }
    
    
}
