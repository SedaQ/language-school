package com.fi.ls.dao;

import com.fi.ls.entity.Student;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Matúš
 */
public interface StudentDao {
    
    /**
     * creates new Student in database 
     * @param s specific Student to be created
     */
    public void create(Student s);
    
    /**
     * updates given Student
     * @param s Student that would be updated
     * @return updated Student
     */
    public Student update(Student s);
    
    /**
     * removes given Student
     * @param s Student that has to be removed
     */
    public void remove(Student s);
    
    /**
     * finds specific student from database by birth number
     * @param birthNumber of student
     * @return student that is found by birth number
     */
    public Student findByBirthNumber(String birthNumber);
    
    /**
     * finds specific Student by id
     * @param id of a Student that would be returned
     * @return specific Student by id
     */
    public Student findById(Long id);
    
    /**
     * returns all Students
     * @return List of all Students
     */
    public Set<Student> findAll();
    
    /**
     * returns all students with same first name
     * @param firstName of students
     * @return list of all students with specific first name
     */
    public Set<Student> findByFirstName(String firstName);
    
    /**
     * returns all students with same surname
     * @param surname of students
     * @return list of all students with specific surname
     */
    public Set<Student> findBySurname(String surname);   

}
