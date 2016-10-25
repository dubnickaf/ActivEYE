package cz.muni.fi.pa165.activeye.dao.impl;

import cz.muni.fi.pa165.activeye.dao.ActivityDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   25-10-2016
 */

@Repository
public class ActivityDaoImpl implements ActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Activity a) {
        if (a == null)
            throw new IllegalArgumentException("Activity should not be null.");
        // No need to check if id == null, @GeneratedValue is called during the insert process on db level. The JPA provider will ensure that the entity is updated afterwards! (this means I can set id to whatever, it will be overriden???)

        em.persist(a);
    }

    @Override
    public void update(Activity a) {
        if (a == null)
            throw new IllegalArgumentException("Activity should not be null.");

        em.merge(a);
    }

    @Override
    public void delete(Activity a) {
        if (a == null)
            throw new IllegalArgumentException("Activity should not be null.");

        em.remove(a);
    }

    @Override
    public Activity findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID should not be null.");

        return em.find(Activity.class, id);
    }

    @Override
    public Activity findByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name should not be null.");

        try {
              return (Activity) em.createQuery("SELECT a FROM Activity a WHERE a.name =:name").setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Activity> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT a FROM Activity a").getResultList());
    }

}
