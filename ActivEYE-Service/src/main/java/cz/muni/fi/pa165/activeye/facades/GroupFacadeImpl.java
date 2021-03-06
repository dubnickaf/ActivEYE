package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
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
        if (group == null) {
            throw new IllegalArgumentException("Cannot create null GroupDTO");
        }
        Group gr = beanMappingService.mapTo(group, Group.class);
        groupService.create(gr);
        group.setId(gr.getId());
    }

    @Override
    public void update(GroupDTO group) {
        if (group == null) {
            throw new IllegalArgumentException("Cannot update null GroupDTO");
        }
        Group gr = beanMappingService.mapTo(group, Group.class);
        groupService.update(gr);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot delete Group of id null");
        }
        Group group = groupService.findById(id);

        groupService.delete(group);
    }

    @Override
    public GroupDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find GroupDTO by null Id");
        }
        Group group =  groupService.findById(id);

        return beanMappingService.mapTo(group, GroupDTO.class);
    }

    @Override
    public Collection<GroupDTO> findAll() {
        Collection<Group> groups = groupService.findAll();
        if(groups == null) {
            throw new NoSuchEntityFoundException("No Groups found");
        }
        return beanMappingService.mapTo(groups, GroupDTO.class);
    }

    @Override
    public boolean isUserInGroup(UserDTO user, GroupDTO group) {
        return groupService.isUserInGroup(beanMappingService.mapTo(user, User.class),
                beanMappingService.mapTo(group, Group.class));
    }

    @Override
    public Collection<UserDTO> getAllUsers(Long id) {
        Collection<User> users = groupService.getAllUsers(id);
        if(users == null) {
            throw new NoSuchEntityFoundException("No Users found in Group");
        }
        return beanMappingService.mapTo(users, UserDTO.class);
    }

    @Override
    public void addUser(UserDTO user, Long id) {
        groupService.addUser(beanMappingService.mapTo(user, User.class), id);
    }
}
