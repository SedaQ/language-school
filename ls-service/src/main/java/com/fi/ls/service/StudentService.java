package com.fi.ls.service;

import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.entity.Student;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matúš
 */
@Service
public interface StudentService {
    
    /**
     * creates specific student in database
     * @param s - student that is created
     * @return created student
     */
    public Student create(Student s);
    
    /**
     * updates specific student in database
     * @param s - student that is updated
     * @return updated student
     */
    public Student update(Student s);
    
    /**
     * removes specific student from database
     * @param s - student to be removed
     */
    public void remove(Student s);
    
    /**
     * finds specific student from database by birth number
     * @param birthNumber of student
     * @return student that is found by birth number
     */
    public Student findByBirthNumber(String birthNumber);
    
    /**
     * finds specific student from database by email
     * @param email of student
     * @return student that is found by email
     */
//    public Student findByEmail(String email);
    
    /**
     * finds specific student from database by id
     * @param id of student
     * @return student that is found by id
     */
    public Student findById(Long id);
    
    /**
     * returns all students from database
     * @return list of all students
     */
    public List<Student> findAllStudents();
    
    /**
     * returns all students with same first name
     * @param firstName of students
     * @return list of all students with specific first name
     */
    public List<Student> findByFirstName(String firstName);
    
    /**
     * returns all students with same surname
     * @param surname of students
     * @return list of all students with specific surname
     */
    public List<Student> findBySurname(String surname);
    
    /**
     * students enroll to specific course
     * @param c - course to be enrolled
     * @param s - student enrolling to course
     */ 
    public void enrollCourse(Course c, Student s);
	
    /**
     * students enroll to specific lecture
     * @param l - lecture to be enrolled
     * @param s - student enrolling to lecture
     */
    public void enrollLecture(Lecture l, Student s);
	
    /**
     * students cancel lecture
     * @param l - lecture which will be canceled 
     * @param s - student which will cancel some lecture
     */
    public void cancelLectureFromStudentsList(Lecture l, Student s);
	
    /**
     * students cancel lectures
     * @param l - lectures which will be canceled 
     * @param s - student which will cancel some lecture
     */
    public void cancelListOfLecturesFromStudentsList(List<Lecture> l, Student s);

}
