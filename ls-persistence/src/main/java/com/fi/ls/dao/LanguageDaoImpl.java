package com.fi.ls.dao;

import com.fi.ls.entity.Language;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Repository
public class LanguageDaoImpl implements LanguageDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Language lan) {
		em.persist(lan);
	}

	@Override
	public Language findById(Long id) {
		return em.find(Language.class, id);
	}

	@Override
	public Language update(Language lan) {
		return em.merge(lan);
	}

	@Override
	public void remove(Language lan) {
		Language toRemove = em.getReference(Language.class, lan.getId());
		em.remove(toRemove);
	}

	@Override
	public Set<Language> findAll() {
		return new HashSet<>(em.createNamedQuery("Language.findAll", Language.class).getResultList());
	}

}
