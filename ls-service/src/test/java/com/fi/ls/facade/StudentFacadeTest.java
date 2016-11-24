package com.fi.ls.facade;

import com.fi.ls.config.BeanMappingConfiguration;
import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.student.StudentCreateDTO;
import com.fi.ls.dto.student.StudentDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Lecturer;
import com.fi.ls.entity.Student;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author Matúš
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StudentFacadeTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    StudentService studentService;
    
    @Mock
    BeanMapping beanMapping;
    
    StudentFacade studentFacade;
    
    StudentDTO student;
    StudentCreateDTO studentCreate;
    LectureDTO lecture;
    CourseDTO course;
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Student())).when(beanMapping).mapTo(any(StudentDTO.class), eq(Student.class));
        doReturn(Optional.of(new Student())).when(beanMapping).mapTo(any(StudentCreateDTO.class), eq(Student.class));
        doReturn(Optional.of(new StudentDTO())).when(beanMapping).mapTo(any(Student.class), eq(StudentDTO.class));
        doReturn(Optional.of(new Lecture())).when(beanMapping).mapTo(any(LectureDTO.class), eq(Lecture.class));
        doReturn(Optional.of(new Course())).when(beanMapping).mapTo(any(CourseDTO.class), eq(Course.class));
        
        studentFacade = new StudentFacadeImpl(studentService, beanMapping);
    }
    
    @BeforeMethod
    public void beforeMethod() {
        student = new StudentDTO();
        studentCreate = new StudentCreateDTO();
        lecture = new LectureDTO();
        course = new CourseDTO();
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(studentService);
    }
    
    @Test
    public void testCreateStudent(){
        studentFacade.createStudent(studentCreate);
        verify(studentService, times(1)).create(any(Student.class));
    }
    
    @Test
    public void testUpdateStudent(){
        studentFacade.updateStudent(student);
        verify(studentService, times(1)).update(any(Student.class));
    }
    
    @Test
    public void testDeleteStudent(){
        studentFacade.deleteStudent(student);
        verify(studentService, times(1)).remove(any(Student.class));
    }
    
    @Test
    public void testGetStudentById(){
        studentFacade.getStudentById(Long.MAX_VALUE);
        verify(studentService, times(1)).findById(any(Long.class));
    }
    
    @Test
    public void testGetAllStudents(){
        studentFacade.getAllStudents();
        verify(studentService, times(1)).findAllStudents();
    }
    
    @Test
    public void testGetStudentByBirthNumber(){
        studentFacade.getStudentByBirthNumber("test");
        verify(studentService, times(1)).findByBirthNumber(any(String.class));
    }
    
    @Test
    public void testGetStudentsByFirstName(){
        studentFacade.getStudentsByFirstName("test");
        verify(studentService, times(1)).findByFirstName(any(String.class));
    }
    
    @Test
    public void testGetStudentsBySurname(){
        studentFacade.getStudentsBySurname("test");
        verify(studentService, times(1)).findBySurname(any(String.class));
    }
    
    @Test
    public void testEnrollCourse(){
        studentFacade.enrollCourse(course, student);
        verify(studentService, times(1)).enrollCourse(any(Course.class), any(Student.class));
    }
    
    @Test
    public void testEnrollLecture(){
        studentFacade.enrollLecture(lecture, student);
        verify(studentService, times(1)).enrollLecture(any(Lecture.class), any(Student.class));
    }
    
    @Test
    public void testCancelLectureFromStudentsList(){
        studentFacade.cancelLectureFromStudentsList(lecture, student);
        verify(studentService, times(1)).cancelLectureFromStudentsList(any(Lecture.class), any(Student.class));
    }
    
    @Test
    public void testCancelListOfLecturesFromStudentsList(){
        List<LectureDTO> l = new ArrayList<>();
        l.add(lecture);
        studentFacade.cancelListOfLecturesFromStudentsList(l, student);
        verify(studentService, times(1)).cancelListOfLecturesFromStudentsList(any(List.class), any(Student.class));
    }
}
