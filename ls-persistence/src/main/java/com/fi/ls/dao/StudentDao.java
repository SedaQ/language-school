package com.fi.ls.dao;

import com.fi.ls.entity.Student;
import java.util.List;

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
	 * finds specific Student by id
	 * @param id of a Student that would be returned
	 * @return specific Student by id
	 */
	public Student findById(Long id);
	
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
	 * returns all Students
	 * @return List of all Students
	 */
	public List<Student> findAll();
    
}
