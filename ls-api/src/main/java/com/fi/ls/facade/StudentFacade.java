package com.fi.ls.facade;

import com.fi.ls.dto.StudentDTO;
import java.util.List;

/**
 *
 * @author Matúš
 */
public interface StudentFacade {
    
    /**
     * creates new student in database
     * @param s - student to be created
     */
    public void create(StudentDTO s);
    
    /**
     * finds specific student in database by id
     * @param id of student
     * @return student that is found by id
     */
    public StudentDTO getStudentById(Long id);
    
    /**
     * updates specific student in database
     * @param s - student that is updated
     * @return updated student
     */
    public StudentDTO update(StudentDTO s);
    
    /**
     * removes specific student from database
     * @param s - student to be removed
     */
    public void remove (StudentDTO s);
    
    /**
     * returns all students in database
     * @return list of all students
     */
    public List<StudentDTO> getAllStudents();
}
