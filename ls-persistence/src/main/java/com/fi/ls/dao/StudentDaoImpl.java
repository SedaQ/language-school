package com.fi.ls.dao;

import com.fi.ls.entity.Student;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Matúš
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Student s) {
	em.persist(s);
    }
    
    @Override
    public Student update(Student s) {
	return em.merge(s);
    }
    
    @Override
    public void remove(Student s) {
    	em.remove(em.getReference(Student.class, s.getId()));
    }

    @Override
    public Student findByBirthNumber(String birthNumber) {
    	return em.createQuery("SELECT s FROM Student s WHERE s.birthNumber=:birthNumber", Student.class)
			.setParameter("birthNumber", birthNumber).getSingleResult();
    }
    
    @Override
    public Student findById(Long id) {
	return em.find(Student.class, id);
    }
    
    @Override
    public Set<Student> findAll() {
    	return new HashSet<>(em.createNamedQuery("Student.findAll", Student.class).getResultList());
    }
    
    @Override
    public Set<Student> findByFirstName(String firstName) {
    	return new HashSet<>(em.createQuery("SELECT s FROM Student s WHERE s.firstName=:firstName", Student.class)
    			.setParameter("firstName", firstName).getResultList());
    }

    @Override
    public Set<Student> findBySurname(String surname) {
    	return new HashSet<>(em.createQuery("SELECT s FROM Student s WHERE s.surname=:surname", Student.class)
			.setParameter("surname", surname).getResultList());
    }

}
