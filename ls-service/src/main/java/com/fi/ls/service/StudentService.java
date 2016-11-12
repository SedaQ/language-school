package com.fi.ls.service;

import com.fi.ls.entity.Student;
import java.util.List;

/**
 *
 * @author Matúš
 */
public interface StudentService {
        
    /**
     * creates new student in database
     * @param s - student to be created
     */
    public void create(Student s);
    
    /**
     * finds specific student in database by id
     * @param id of student
     * @return student that is found by id
     */
    public Student findById(Long id);
    
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
    public void remove (Student s);
    
    /**
     * returns all students in database
     * @return list of all students
     */
    public List<Student> findAll();
}
