package com.fi.ls.dao;

import com.fi.ls.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Student update(Student s) {
        return em.merge(s);
    }

    @Override
    public void remove(Student s) {
        em.remove(findById(s.getId()));
    }

    @Override
    public List<Student> findAll() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }
    
}
