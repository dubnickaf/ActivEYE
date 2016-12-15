package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import java.util.Collection;

/**
 * @author Branislav Bajužík; 445772
 */
public interface GroupService {

    /**
     * Creates Group
     * @param group Group to be created
     * @throws IllegalArgumentException if group is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    void create(Group group);

    /**
     * Updates Group
     * @param group Group to be updated
     * @throws IllegalArgumentException if group is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    void update(Group group);

    /**
     * Updates Group
     * @param group Group to be updated
     * @throws IllegalArgumentException if group is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    void delete(Group group);

    /**
     *
     * @param id id of the Group to be found
     * @return Found Group
     * @throws IllegalArgumentException if id is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     * @throws cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException if The group isnt found
     */
    Group findById(Long id);

    /**
     * Gets all groups
     * @return All groups
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    Collection<Group> findAll();

    /**
     * returns true if User is in group, false otherwise
     * @param user User to be found
     * @param group Group to search in
     * @return returns true if User is in group, false otherwise
     * @throws IllegalArgumentException if group or user is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    boolean isUserInGroup(User user, Group group);

    /**
     * gets all users of a group
     * @param id of group to search in
     * @return all users of a group
     * @throws IllegalArgumentException if id is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    Collection<User> getAllUsers(Long id);

    /**
     * Adds User to Group
     * @param user User to be added
     * @param id of Group to be added to
     * @throws IllegalArgumentException if id or user is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException if there is error in DAO layer
     */
    void addUser(User user, Long id);
}
