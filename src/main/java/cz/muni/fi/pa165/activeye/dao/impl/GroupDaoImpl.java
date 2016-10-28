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
        if (group == null) {
            throw new IllegalArgumentException("Group can't be null");
        }
        em.persist(group);
    }

    @Override
    public void update(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Group can't be null");
        }
        em.merge(group);
    }

    @Override
    public void delete(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Group can't be null");
        }
        em.remove(em.contains(group) ? group : em.merge(group));
    }

    @Override
    public Group findGroupById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return em.find(Group.class, id);
    }

    @Override
    public List<User> findAllGroups() {
        return em.createQuery("SELECT g FROM Group g").getResultList();
    }

    @Override
    public boolean isUserInGroup(Group group, User user) {
        return findGroupById(group.getId()).getUsers().contains(user);
    }

    @Override
    public Set<User> getAllGroupUsers(Group group) {
        return findGroupById(group.getId()).getUsers();
    }
}
