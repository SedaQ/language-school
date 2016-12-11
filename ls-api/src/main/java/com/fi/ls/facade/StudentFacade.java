package com.fi.ls.facade;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.dto.student.StudentCreateDTO;
import com.fi.ls.dto.student.StudentDTO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Matúš
 */
public interface StudentFacade {
    
    /**
     * creates specific student in database
     * @param s - student that is created
     */
    public Optional<StudentDTO> createStudent(StudentCreateDTO s);
    
    /**
     * updates specific student in database
     * @param s - student that is updated
     * @return updated student
     */
    public Optional<StudentDTO> updateStudent(StudentDTO s);
    
    /**
     * removes specific student from database
     * @param s - student to be removed
     * @return true, if successfully removed
     */
    public Boolean deleteStudent(StudentDTO s);
    
    /**
     * finds specific student from database by birth number
     * @param birthNumber of student
     * @return student that is found by birth number
     */
    public Optional<StudentDTO> getStudentByBirthNumber(String birthNumber);
    
    /**
     * finds specific student from database by id
     * @param id of student
     * @return student that is found by id
     */
    public Optional<StudentDTO> getStudentById(Long id);
    
    /**
     * returns all students from database
     * @return list of all students
     */
    public List<StudentDTO> getAllStudents();
    
    /**
     * returns all students with same first name
     * @param firstName of students
     * @return list of all students with specific first name
     */
    public List<StudentDTO> getStudentsByFirstName(String firstName);
    
    /**
     * returns all students with same surname
     * @param surname of students
     * @return list of all students with specific surname
     */
    public List<StudentDTO> getStudentsBySurname(String surname);
    
    /**
     * students enroll to specific course
     * @param c - course to be enrolled
     * @param s - student enrolling to course
     */ 
    public void enrollCourse(CourseDTO c, StudentDTO s);
	
    /**
     * students enroll to specific lecture
     * @param l - lecture to be enrolled
     * @param s - student enrolling to lecture
     */
    public void enrollLecture(LectureDTO l, StudentDTO s);

    /**
     * students cancel lecture
     * @param l - lecture which will be canceled 
     * @param s - student which will cancel some lecture
     */
    public void cancelLectureFromStudentsList(LectureDTO l, StudentDTO s);
	
    /**
     * students cancel lectures
     * @param l - lectures which will be canceled 
     * @param s - student which will cancel some lecture
     */
    public void cancelListOfLecturesFromStudentsList(List<LectureDTO> l, StudentDTO s);
}
