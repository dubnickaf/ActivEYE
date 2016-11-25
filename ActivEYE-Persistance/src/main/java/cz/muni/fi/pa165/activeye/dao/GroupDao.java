package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.entities.Group;

import java.util.List;
import java.util.Set;

/**
 * @author Branislav Bajuzik; 442772
 */
public interface GroupDao {
    /**
     * Create Group in database
     * @param group Group to create
     */
    void create(Group group);

    /**
     * Update Group in database
     * @param group Group to be updated
     */
    void update(Group group);

    /**
     * Delete group in database
     * @param group Group to be deleted
     */
    void delete(Group group);

    /**
     * Find Group by Id
     * @param id Id of the Group to be found
     * @return Found Group if exists in database, null otherwise
     */
    Group findById(Long id);

    /**
     * Find all Groups
     * @return List<Group> of all Groups
     */
    List<Group> findAll();

    /**
     * Check if User is in Group
     * @param user User to be found
     * @param group Group to be found in
     * @return True if found, else otherwise
     */
    boolean isUserInGroup(User user, Group group);

    /**
     * Get all Users of Group
     * @param group Group we want Users of
     * @return Set of all Users in Group
     */
    Set<User> getAllUsers(Group group);

    /**
     * Adds user to Group
     * @param user user to be added
     * @param group group where to be added
     * @throws IllegalArgumentException when User is null
     */
    void addUser(User user, Group group);
}
