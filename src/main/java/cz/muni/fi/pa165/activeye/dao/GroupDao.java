package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import java.util.List;
import java.util.Set;

/**
 * @author Branislav Bajuzik; 442772
 */
public interface GroupDao {
    /**
     *
     * @param group
     */
    void create(Group group);

    /**
     *
     * @param group
     */
    void update(Group group);

    /**
     *
     * @param group
     */
    void delete(Group group);

    /**
     *
     * @param id
     * @return
     */
    Group findGroupById(Long id);

    /**
     *
     * @return
     */
    List<User> findAllGroups();

    /**
     *
     * @param user
     * @return
     */
    boolean isUserInGroup(Group group, User user);

    /**
     *
     * @param group
     * @return
     */
    Set<User> getAllGroupUsers(Group group);
}
