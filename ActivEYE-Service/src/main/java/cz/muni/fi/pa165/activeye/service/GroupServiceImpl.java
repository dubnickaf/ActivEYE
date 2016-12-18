package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.GroupDao;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.util.Collection;
import java.util.Set;

/**
 * @author Branislav Bajužík; 445772
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Inject
    private GroupDao groupDao;

    @Inject
    private UserService userService;

    @Override
    public void create(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't create null Group");
        }
        try {
            groupDao.create(group);
            for(User user : group.getUsers()) {
                user.getGroups().add(group);
                userService.updateUser(user);
            }
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public void update(Group group) {
        if (group == null) {
            throw new IllegalArgumentException("Can't update null Group");
        }
        if (group.getId() == null) {
            throw new IllegalArgumentException("Group's id cannot be null.");
        }
        if (group.getName() == null) {
            throw new IllegalArgumentException("Group's name cannot be null.");
        }
        try {
            groupDao.update(group);
            for (User user : group.getUsers()) {
                if(!user.getGroups().contains(group)) {
                    user.getGroups().add(group);
                    userService.updateUser(user);
                }
            }
        } catch (IllegalArgumentException | TransactionRequiredException e) {
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
            if(group.getUsers() != null) {
                for (User user : group.getUsers()) {
                    Set<Group> usersGroups = user.getGroups();
                    usersGroups.remove(group);
                    user.setGroups(usersGroups);
                    userService.updateUser(user);
                }
            }
            groupDao.delete(group);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
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
        } catch (IllegalArgumentException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
        if(g == null) {
            throw new NoSuchEntityFoundException("No Group with id " + id + " found");
        }
        return g;
    }

    @Override
    public Collection<Group> findAll() {
        return groupDao.findAll();
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
        } catch (IllegalArgumentException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public Collection<User> getAllUsers(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Can't get contents of null id Group");
        }
        try {
            return groupDao.getAllUsers(id);
        } catch (IllegalArgumentException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }

    @Override
    public void addUser(User user, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Can't add to null Group of id null");
        }
        if (user == null) {
            throw new IllegalArgumentException("Can't add null User");
        }
        try {
            groupDao.addUser(user, groupDao.findById(id));
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer", e);
        }
    }
}
