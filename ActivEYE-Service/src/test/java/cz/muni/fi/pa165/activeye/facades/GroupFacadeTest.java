package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.activeye.service.GroupService;
import java.util.Collection;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   25-11-2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GroupFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupFacade groupFacade = new GroupFacadeImpl();

    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    private Group group;
    private User user1;
    private User user2;

    private GroupDTO groupDTO;
    private UserDTO user1DTO;
    private UserDTO user2DTO;

    private HashSet<User> users = new HashSet<User>();

    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() {
        group = new Group();
        user1 = new User();
        user2 = new User();

        group.setId(0l);
        group.setCreatorsUserId(0l);
        group.setName("Atleti");

        user1.setId(0l);
        user1.setName("Carl Pilkington");
        user1.setEmailAddress("carl.pilkington@headlikeanorange.co.uk");
        user2.setId(1l);
        user2.setName("Llyonel Monde");
        user2.setEmailAddress("llyonel.monde@mondecreations.com");

        users.add(user1);
        users.add(user2);

        groupDTO = beanMappingService.mapTo(group, GroupDTO.class);
        user1DTO = beanMappingService.mapTo(user1, UserDTO.class);
        user2DTO = beanMappingService.mapTo(user2, UserDTO.class);
    }

    @Test
    public void testCreateGroup() {
        groupFacade.create(groupDTO);
        verify(groupService).create(group);
    }

    @Test
    public void testUpdateGroup() {
        groupFacade.update(groupDTO);
        verify(groupService).update(group);
    }

    @Test
    public void testDeleteGroup() {
        groupFacade.delete(groupDTO);
        verify(groupService).delete(group);
    }

    @Test
    public void testFindById() {
        when(groupService.findById(group.getId())).thenReturn(group);
        GroupDTO returnedGroupDTO = groupFacade.findById(group.getId());

        Assert.assertEquals(beanMappingService.mapTo(returnedGroupDTO, Group.class), group);
    }

    @Test
    public void testFindAll() {
        when(groupService.findAll()).thenReturn(Arrays.asList(group));
        Collection<GroupDTO> returnedGroupDTOList = groupFacade.findAll();

        Assert.assertEquals(beanMappingService.mapTo(returnedGroupDTOList.toArray()[0], Group.class), group);
        Assert.assertEquals(returnedGroupDTOList.size(), 1);
    }

    @Test
    public void testIsUserInGroup() {
        when(groupService.isUserInGroup(user1, group)).thenReturn(true);
        when(groupService.isUserInGroup(user2, group)).thenReturn(false);

        Assert.assertTrue(groupFacade.isUserInGroup(user1DTO, groupDTO));
        Assert.assertFalse(groupFacade.isUserInGroup(user2DTO, groupDTO));
    }

    @Test
    public void testGetAllUsers() {
        when(groupService.getAllUsers(group)).thenReturn(users);

        Collection<UserDTO> userCollection = groupFacade.getAllUsers(groupDTO);

        Assert.assertEquals(2, userCollection.size());
        Assert.assertEquals(beanMappingService.mapTo(userCollection.toArray()[1], User.class), user1);
        Assert.assertEquals(beanMappingService.mapTo(userCollection.toArray()[0], User.class), user2);
    }

    @Test
    public void testAddUser() {
        groupFacade.addUser(user1DTO, groupDTO);
        verify(groupService).addUser(user1, group);
    }

}
