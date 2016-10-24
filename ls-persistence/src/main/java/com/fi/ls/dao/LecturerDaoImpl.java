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
public class LecturerDaoImpl implements LecturerDao {

	@PersistenceContext
	private EntityManager em;

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
		Lecturer toRemove = em.getReference(Lecturer.class, l.getId());
		em.remove(toRemove);
	}

	@Override
	public List<Lecturer> findAll() {
		return em.createNamedQuery("Lecturer.findAll", Lecturer.class).getResultList();
	}

}
