package cz.muni.fi.pa165.activeye.dao.impl;

import cz.muni.fi.pa165.activeye.dao.ActivityDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   25-10-2016
 */

@Repository
@Transactional
public class ActivityDaoImpl implements ActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Activity a) {
        validateActivity(a);
        // No need to check if id == null, @GeneratedValue is called during the insert process on db level. The JPA provider will ensure that the entity is updated afterwards! (this means I can set id to whatever, it will be overriden???)

        em.persist(a);
    }

    @Override
    public void update(Activity a) {
        validateActivity(a);

        em.merge(a);
    }

    @Override
    public void delete(Activity a) {
        validateActivity(a);

        //em.remove(a);
        em.remove(em.contains(a) ? a : em.merge(a));
    }

    @Override
    public Activity findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID should not be null.");
        if (id < 1)
            throw new IllegalArgumentException("Activity id can't be < 1.");
        return em.find(Activity.class, id);
    }

    @Override
    public Activity findByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name should not be null.");
        if (name.isEmpty())
            throw new IllegalArgumentException("Name should not be empty.");

        try {
              return (Activity) em.createQuery("SELECT a FROM Activity a WHERE a.name =:name").setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Activity> findAll() {
        return em.createQuery("SELECT a FROM Activity a",Activity.class).getResultList();
    }

    private static void validateActivity(Activity activity) {
        if (activity == null)
            throw new IllegalArgumentException("Activity can't be null.");
        if (activity.getId() != null && activity.getId() < 1)
            throw new IllegalArgumentException("Activity id can't be < 1.");
        if (activity.getName() == null)
            throw new IllegalArgumentException("Activity name can't be null.");
        if (activity.getName().isEmpty())
            throw new IllegalArgumentException("Activity name can't be empty.");
        if (activity.getCaloriesRatio() == null)
            throw new IllegalArgumentException("Activity caloriesRatio can't be null");
        if (activity.getCaloriesRatio().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Activity caloriesRatio can't be <= 0");
    }

}
