package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Student;
import org.mockito.Mock;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Matúš
 */
public class StudentServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private StudentDao studentDao;
    
    private StudentService studentService;
    
    Student s1;
    Student s2;
    Student s3;
    
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
        
        s1.setFirstName("enterprise");
        s2.setFirstName("enterprise");
        s3.setFirstName("enterprise3");
        
        s1.setSurname("java1");
        s2.setSurname("java");
        s3.setSurname("java");
        
        //s1.setPasswordHash(UserPasswordEncryption.createHash("project1"));
        //s2.setPasswordHash(UserPasswordEncryption.createHash("project2"));
        //s3.setPasswordHash(UserPasswordEncryption.createHash("project3"));
    }

}
