package cz.muni.fi.pa165.activeye.dao.impl;

import cz.muni.fi.pa165.activeye.dao.GroupDao;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

/**
 * @author Branislav Bajuzik; 445772
 */
@Repository
@Transactional
public class GroupDaoImpl implements GroupDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Group group) {
        em.persist(group);
    }

    @Override
    public void update(Group group) {
        em.merge(group);
    }

    @Override
    public void delete(Group group) {
        em.remove(em.contains(group) ? group : em.merge(group));
    }

    @Override
    public Group findById(Long id) {
        return em.find(Group.class, id);
    }

    @Override
    public List<Group> findAll() {
        return em.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }

    @Override
    public boolean isUserInGroup(User user, Group group) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
        return findById(group.getId()).getUsers().contains(user);
    }

    @Override
    public Set<User> getAllUsers(Long id) {
        return findById(id).getUsers();
    }

    @Override
    public void addUser(User user, Group group) {
        group.addUser(user);
        update(group);
    }
}
