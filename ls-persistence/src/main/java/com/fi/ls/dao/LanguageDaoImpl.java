package com.fi.ls.dao;

import com.fi.ls.entity.Language;
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
public class LanguageDaoImpl implements LanguageDao {

    @PersistenceContext
    private EntityManager em;
   
    private final String findAllQuerry = "SELECT lan FROM Language lan";
    
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
        Language toRemove = em.getReference(Language.class, lan);
        em.remove(toRemove);
        
    }

    @Override
    public List<Language> findAll() {
        return em.createQuery(findAllQuerry).getResultList();
    }
    
}
