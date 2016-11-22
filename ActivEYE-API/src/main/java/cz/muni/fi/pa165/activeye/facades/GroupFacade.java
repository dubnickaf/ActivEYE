package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import java.util.Collection;

/**
 * @author Branislav Bajuzik; 442772
 */
public interface GroupFacade {

    void create(GroupDTO group);

    void update(GroupDTO group);

    void delete(GroupDTO group);

    GroupDTO findById(Long id);

    Collection<GroupDTO> findAll();

    boolean isUserInGroup(UserDTO user, GroupDTO group);

    Collection<UserDTO> getAllUsers(GroupDTO group);

    void addUser(UserDTO user, GroupDTO group);
}
