package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import java.util.Collection;

/**
 * @author Branislav Bajuzik; 442772
 */
public interface GroupFacade {

    /**
     * Create Group
     * @param group group to be created
     * @throws IllegalArgumentException when group is null
     */
    void create(GroupDTO group);

    /**
     * Updates Group
     * @param group group to be updated
     * @throws IllegalArgumentException if group is null
     */
    void update(GroupDTO group);

    /**
     * Deletes Group
     * @param group group to be deleted
     * @throws IllegalArgumentException if group is null or wasn't persisted yet
     */
    void delete(GroupDTO group);

    /**
     * Finds Group by id
     * @param id id of the group to be found
     * @return found group
     * @throws IllegalArgumentException if id is null
     * @throws cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException if Group wasn't find
     */
    GroupDTO findById(Long id);

    /**
     * Returns all groups
     * @return all groups
     * @throws cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException if there are no groups
     */
    Collection<GroupDTO> findAll();

    /**
     * Returns true if user is in Group, false otherwise
     * @param user User to be found
     * @param group Group to search in
     * @return true if user is in Group, false otherwise
     */
    boolean isUserInGroup(UserDTO user, GroupDTO group);

    /**
     * gets all users of group
     * @param group Group to get Users of
     * @return all users of the group
     * @throws cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException if there are no users in group
     */
    Collection<UserDTO> getAllUsers(GroupDTO group);

    /**
     * Adds User to Group
     * @param user User to be added
     * @param group Group to be added to
     */
    void addUser(UserDTO user, GroupDTO group);
}
