package com.fi.ls.dao;

import com.fi.ls.entity.Lecturer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Repository
@Transactional
public class LecturerDaoImpl implements LecturerDao {

    @PersistenceContext
    private EntityManager em;
    
    private final String findAllQuerry = "SELECT l FROM Lecturer l";
    
    @Override
    public void create(Lecturer l) {
         em.persist(l);
    }

    @Override
    public Lecturer findById(Long id) {
        return em.find(Lecturer.class, id);
    }

    @Override
    public Lecturer update(Lecturer l) {
        return em.merge(l);
    }

    @Override
    public void remove(Lecturer l) {
        Lecturer toRemove = em.getReference(Lecturer.class, l);
        em.remove(toRemove);
    }

    @Override
    public List<Lecturer> findAll() {
        return em.createQuery(findAllQuerry).getResultList();
    }
    
}
