package com.fi.ls.service;

import com.fi.ls.dao.StudentDao;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import com.fi.ls.enums.ProficiencyLevel;
import com.fi.ls.exceptions.ServiceLayerException;
import java.time.LocalDateTime;
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
    
    Course c;
    
    Lecture l1;
    Lecture l2;
    Lecture l3;
    
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
        
        l1 = new Lecture();
        l2 = new Lecture();
        l3 = new Lecture();
        
        l1.setDayTime(LocalDateTime.now());
        l2.setDayTime(LocalDateTime.now());
        l3.setDayTime(LocalDateTime.now());
        
        l1.setTopic("Something1");
        l2.setTopic("Something2");
        l3.setTopic("Something3");
        
        l1.setClassroomId("1");
        l2.setClassroomId("2");
        l3.setClassroomId("3");
        
        c = new Course();
        c.setName("test course");
        c.setLanguage("java");
        c.setProficiencyLevel(ProficiencyLevel.A1);
        c.addLecture(l1);
        c.addLecture(l2);
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
    /*
    @Test
    public void testFindByFirstName(){
        studentService.create(s1);
        studentService.create(s2);
        studentService.create(s3);

        assertEquals(studentService.findByFirstName("enterprise").size(), 2);
    }*/
    
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
    /*
    @Test
    public void testFindBySurname(){
        studentService.create(s1);
        studentService.create(s2);
        studentService.create(s3);
        
        assertEquals(studentService.findBySurname("java").size(), 2);
    }*/
    
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
    
    @Test
    public void testEnroll(){
        studentService.enrollCourse(c, s1);
        studentService.enrollLecture(l3, s1);
        assertEquals(s1.getListOfLectures().size(), 3);
    }
    
    @Test
    public void testEnrollCourse(){
        studentService.enrollCourse(c, s1);
        assertEquals(s1.getListOfLectures().size(), 2);
    }
    
    @Test
    public void testEnrollCourse2(){
        Student s4 = new Student();
        s4.setBirthNumber("4");
        s4.setEmail("jj.jj@jjj.jj");
        s4.setPasswordHash("hashtag");
        s4.setFirstName("eee");
        s4.setSurname("rrr");
        studentService.enrollCourse(c, s4);
        verify(studentDao, times(1)).update(s4);
    }
    
    @Test
    public void testEnrollCourseTwice(){
        studentService.enrollCourse(c, s1);
        studentService.enrollCourse(c, s1);
        assertEquals(s1.getListOfLectures().size(), 2);
    }
    
    @Test
    public void testEnrollCourseAndCoursesLecture(){
        studentService.enrollCourse(c, s1);
        studentService.enrollLecture(l1, s1);
        assertEquals(s1.getListOfLectures().size(), 2);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testEnrollCourseNullCourse(){
        studentService.enrollCourse(null, s1);
        fail("Enrolled null course.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testEnrollCourseNullStudent(){
        studentService.enrollCourse(c, null);
        fail("Enrolled by null student.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testEnrollCourseNullBoth(){
        studentService.enrollCourse(null, null);
        fail("Enrolled null course by null student.");
    }
    
    @Test
    public void testEnrollLecture(){
        studentService.enrollLecture(l3, s1);
        assertEquals(s1.getListOfLectures().size(), 1);
    }
    
    @Test
    public void testEnrollLecture2(){
        Student s5 = new Student();
        s5.setBirthNumber("5");
        s5.setEmail("jj.jj@jjj.jj");
        s5.setPasswordHash("hashtag");
        s5.setFirstName("eee");
        s5.setSurname("rrr");
        studentService.enrollLecture(l3, s5);
        verify(studentDao, times(1)).update(s5);
    }
    
    @Test
    public void testEnrollLectureTwice(){
        studentService.enrollLecture(l3, s1);
        studentService.enrollLecture(l3, s1);
        assertEquals(s1.getListOfLectures().size(), 1);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testEnrollLectureNullLecture(){
        studentService.enrollLecture(null, s1);
        fail("Enrolled null lecture.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testEnrollLectureNullStudent(){
        studentService.enrollLecture(l3, null);
        fail("Enrolled by null student.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testEnrollLectureNullBoth(){
        studentService.enrollLecture(null, null);
        fail("Enrolled null lecture by null student.");
    }
    
    @Test
    public void testCancelLectureFromStudentsList(){
        assertEquals(s1.getListOfLectures().size(), 0);
        studentService.cancelLectureFromStudentsList(l1, s1);
        assertEquals(s1.getListOfLectures().size(), 0);
        
        studentService.enrollLecture(l3, s1);
        assertEquals(s1.getListOfLectures().size(), 1);
        
        studentService.cancelLectureFromStudentsList(l1, s1);
        assertEquals(s1.getListOfLectures().size(), 1);
        
        studentService.cancelLectureFromStudentsList(l3, s1);
        assertEquals(s1.getListOfLectures().size(), 0);
    }
    
    @Test
    public void testCancelLectureFromStudentsList2(){
        Student s6 = new Student();
        s6.setBirthNumber("6");
        s6.setEmail("jj.jj@jjj.jj");
        s6.setPasswordHash("hashtag");
        s6.setFirstName("eee");
        s6.setSurname("rrr");
        studentService.cancelLectureFromStudentsList(l3, s6);
        verify(studentDao, times(1)).update(s6);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCancelLectureFromStudentsListNullLecture(){
        studentService.cancelLectureFromStudentsList(null, s1);
        fail("Canceled null lecture.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCancelLectureFromStudentsListNullStudent(){
        studentService.cancelLectureFromStudentsList(l1, null);
        fail("Lecture canceled by null student.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCancelLectureFromStudentsListNullBoth(){
        studentService.cancelLectureFromStudentsList(null, null);
        fail("Null lecture canceled by null student.");
    }
    
    @Test
    public void testCancelListOfLecturesFromStudentsList(){
        assertEquals(s1.getListOfLectures().size(), 0);
        studentService.cancelListOfLecturesFromStudentsList(c.getListOfLectures(), s1);
        assertEquals(s1.getListOfLectures().size(), 0);
        
        studentService.enrollLecture(l3, s1);
        assertEquals(s1.getListOfLectures().size(), 1);
        
        studentService.cancelListOfLecturesFromStudentsList(c.getListOfLectures(), s1);
        assertEquals(s1.getListOfLectures().size(), 1);
        
        studentService.enrollCourse(c, s1);
        assertEquals(s1.getListOfLectures().size(), 3);
        
        studentService.cancelListOfLecturesFromStudentsList(c.getListOfLectures(), s1);
        assertEquals(s1.getListOfLectures().size(), 1);
    }
    
    @Test
    public void testCancelListOfLecturesFromStudentsList2(){
        Student s7 = new Student();
        s7.setBirthNumber("7");
        s7.setEmail("jj.jj@jjj.jj");
        s7.setPasswordHash("hashtag");
        s7.setFirstName("eee");
        s7.setSurname("rrr");
        studentService.cancelListOfLecturesFromStudentsList(c.getListOfLectures(), s7);
        verify(studentDao, times(1)).update(s7);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCancelListOfLecturesFromStudentsListNullList(){
        studentService.cancelListOfLecturesFromStudentsList(null, s1);
        fail("Canceled null list of lectures.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCancelListOfLecturesFromStudentsListNullStudent(){
        studentService.cancelListOfLecturesFromStudentsList(c.getListOfLectures(), null);
        fail("List of lectures canceled by null student.");
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCancelListOfLecturesFromStudentsListNullBoth(){
        studentService.cancelListOfLecturesFromStudentsList(null, null);
        fail("Null list of lectures canceled by null student.");
    }
}
