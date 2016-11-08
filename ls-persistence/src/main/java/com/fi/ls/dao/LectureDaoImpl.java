package com.fi.ls.dao;

import com.fi.ls.entity.Lecture;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marek Nedbal (357293)
 */
@Repository
public class LectureDaoImpl implements LectureDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Lecture lecture) {
            
		em.persist(lecture);
                
	}

	@Override
	public Lecture findById(Long id) {
            
		return em.find(Lecture.class, id);
                
	}

	@Override
	public Lecture update(Lecture lecture) {
            
		return em.merge(lecture);
                
	}

	@Override
	public void remove(Lecture lecture) {
		
		em.remove(em.getReference(Lecture.class, lecture.getId()));

	}

	@Override
	public List<Lecture> findAll() {
            
		return em.createNamedQuery("Lecture.findAll", Lecture.class).getResultList();
                
	}
        
}
