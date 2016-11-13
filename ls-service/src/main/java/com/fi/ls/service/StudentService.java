package com.fi.ls.service;

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
    public Student findByEmail(String email);
    
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
     * authenticates student
     * @param s student object with hashed password
     * @param password of user
     * @return true is hashed password matches
     */
    public boolean authenticateStudent(Student s, String password);

    /**
     * creates new student in database
     * @param s - student to be created
     * @param unecryptedPassword of student
     */
	void registerStudent(Student s, String unencryptedPassword);
}
