package cz.muni.fi.pa165.activeye.dao.impl;

import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author Filip Dubnička [445647]
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    private final EntityManager entityManager;

    @Inject
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        if(user == null){
            throw new IllegalArgumentException("User should not be null.");
        }

        if(user.getId() != null){
            throw new IllegalArgumentException("");
        }
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        if(user == null){
            throw new IllegalArgumentException("User should not be null.");
        }
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        if(user == null){
            throw new IllegalArgumentException("User should not be null.");
        }
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public User findUserById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id should not be null.");
        }
        return entityManager.find(User.class,id);
    }

    @Override
    public User findUserByEmail(String email) {
        if(email == null){
            throw new IllegalArgumentException("Email should not be null.");
        }
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.emailAddress =:givenEmail").setParameter("givenEmail", email).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }
}
