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
        validateGroup(group);
        em.persist(group);
    }

    @Override
    public void update(Group group) {
        validateGroup(group);
        if (group.getId() == null)
            throw new IllegalArgumentException("Group's id can't be null");
        em.merge(group);
    }

    @Override
    public void delete(Group group) {
        validateGroup(group);
        if (group.getId() == null)
            throw new IllegalArgumentException("Group's id can't be null");
        if (em.contains(group)) {
            em.remove(group);
        }
    }

    @Override
    public Group findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return em.find(Group.class, id);
    }

    @Override
    public List<Group> findAll() {
        return em.createQuery("SELECT g FROM Group g").getResultList();
    }

    @Override
    public boolean isUserInGroup(User user, Group group) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
        validateGroup(group);
        return findById(group.getId()).getUsers().contains(user);
    }

    @Override
    public Set<User> getAllUsers(Group group) {
        return findById(group.getId()).getUsers();
    }

    @Override
    public void addUser(User user, Group group) {
        group.addUser(user);
        update(group);
    }

    private static void validateGroup(Group group) {
        if (group == null)
            throw new IllegalArgumentException("Group can't be null");
        if (group.getId() != null && group.getId() <1)
            throw new IllegalArgumentException("Group's id can't be < 1");
        if (group.getName() == null)
            throw new IllegalArgumentException("Group's name can't be null");
        if (group.getName().isEmpty())
            throw new IllegalArgumentException("Group's name can't be empty");
        if (group.getUsers() == null)
            throw new IllegalArgumentException("Group's users can't be null");
        if (group.getCreatorsUserId() == null)
            throw new IllegalArgumentException("Group's creatorsUsersId can't be null");
        if (group.getCreatorsUserId() < 1)
            throw new IllegalArgumentException("Group's creatorsUsersId can't be < 1");
        if (group.getGroupSize() < 0)
            throw new IllegalArgumentException("Group's groupSize can't be < 0");
    }
}
