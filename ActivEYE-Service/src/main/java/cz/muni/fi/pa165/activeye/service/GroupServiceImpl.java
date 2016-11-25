package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.GroupDao;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
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
        if (group == null) {
            throw new IllegalArgumentException("Can't create null Group");
        }
        try {
            groupDao.create(group);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public void update(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't update null Group");
        }
        try {
            groupDao.update(group);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public void delete(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't delete null Group");
        }
        if (group.getId() == null) {
            throw new IllegalArgumentException("Group hasn't been persisted yet");
        }
        try {
            groupDao.delete(group);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public Group findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Can't Group of Id null");
        }
        Group g;
        try {
            g = groupDao.findById(id);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
        if(g == null) {
            throw new NoSuchEntityFoundException("No Group with id " + id + " found");
        }
        return g;
    }

    @Override
    public Collection<Group> findAll() {
        try {
            return groupDao.findAll();
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public boolean isUserInGroup(User user, Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't check in null Group");
        }
        if (user == null) {
            throw new IllegalArgumentException("Can't check for null User");
        }
        try {
            return groupDao.isUserInGroup(user, group);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public Collection<User> getAllUsers(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't get contents of null Group");
        }
        try {
            return groupDao.getAllUsers(group);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public void addUser(User user, Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't add to null Group");
        }
        if (user == null) {
            throw new IllegalArgumentException("Can't add null User");
        }
        try {
            groupDao.addUser(user, group);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }
}
