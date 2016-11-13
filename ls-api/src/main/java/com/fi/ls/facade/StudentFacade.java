package com.fi.ls.facade;

import com.fi.ls.dto.StudentDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Matúš
 */
public interface StudentFacade {
    
    /**
     * creates new student in database
     * @param s - student to be created
     * @param unecryptedPassword of student
     */
    public void registerStudent(StudentDTO s, String unecryptedPassword);
    
    /**
     * finds specific student from database by birth number
     * @param birthNumber of student
     * @return student that is found by birth number
     */
    public Optional<StudentDTO> getStudentByBirthNumber(String birthNumber);
    
    /**
     * finds specific student from database by email
     * @param email of student
     * @return student that is found by email
     */
    public Optional<StudentDTO> getStudentByEmail(String email);
    
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
     * updates specific student in database
     * @param s - student that is updated
     * @return updated student
     */
    public Optional<StudentDTO> updateStudent(StudentDTO s);
    
    /**
     * removes specific student from database
     * @param s - student to be removed
     */
    public void deleteStudent(StudentDTO s);
    
    /**
     * authenticates student
     * @param s student object with hashed password
     * @return true is hashed password matches
     */
    public boolean authenticateStudent(StudentDTO s);
}
