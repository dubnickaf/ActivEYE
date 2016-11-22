package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import java.util.Collection;

/**
 * @author Branislav Bajužík; 445772
 */
public interface GroupService {

    void create(Group group);

    void update(Group group);

    void delete(Group group);

    Group findById(Long id);

    Collection<Group> findAll();

    boolean isUserInGroup(User user, Group group);

    Collection<User> getAllUsers(Group group);

    void addUser(User user, Group group);
}
