package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Branislav Bajuzik; 442772
 */
@Service
@Transactional
public class GroupFacadeImpl implements GroupFacade {

    @Inject
    GroupService groupService;

    @Inject
    BeanMappingService beanMappingService;

    @Override
    public void create(GroupDTO group) {
        Group gr = beanMappingService.mapTo(group, Group.class);
        groupService.create(gr);
        group.setId(gr.getId());
    }

    @Override
    public void update(GroupDTO group) {
        Group gr = beanMappingService.mapTo(group, Group.class);
        groupService.update(gr);
    }

    @Override
    public void delete(GroupDTO group) {
        Group gr = beanMappingService.mapTo(group, Group.class);
        groupService.delete(gr);
    }

    @Override
    public GroupDTO findById(Long id) {
        Group group =  groupService.findById(id);
        return (group == null) ? null : beanMappingService.mapTo(group, GroupDTO.class);
    }

    @Override
    public Collection<GroupDTO> findAll() {
        Collection<Group> groups = groupService.findAll();
        return (groups == null) ? null : beanMappingService.mapTo(groups, GroupDTO.class);
    }

    @Override
    public boolean isUserInGroup(UserDTO user, GroupDTO group) {
        return groupService.isUserInGroup(beanMappingService.mapTo(user, User.class), beanMappingService.mapTo(group, Group.class));
    }

    @Override
    public Collection<UserDTO> getAllUsers(GroupDTO group) {
        Collection<User> users = groupService.getAllUsers(beanMappingService.mapTo(group, Group.class));
        return (users == null) ? null : beanMappingService.mapTo(users, UserDTO.class);
    }

    @Override
    public void addUser(UserDTO user, GroupDTO group) {
        groupService.addUser(beanMappingService.mapTo(user, User.class), beanMappingService.mapTo(group, Group.class));
    }
}
