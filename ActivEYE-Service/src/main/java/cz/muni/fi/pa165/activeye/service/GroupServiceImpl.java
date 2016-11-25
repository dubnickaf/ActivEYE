package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.GroupDao;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Branislav Bajužík; 445772
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Inject
    private GroupDao groupDao;

    @Override
    public void create(Group group) {
        groupDao.create(group);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }

    @Override
    public Group findById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public Collection<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public boolean isUserInGroup(User user, Group group) {
        return groupDao.isUserInGroup(user, group);
    }

    @Override
    public Collection<User> getAllUsers(Group group) {
        return groupDao.getAllUsers(group);
    }

    @Override
    public void addUser(User user, Group group) {
        groupDao.addUser(user, group);
    }
}
