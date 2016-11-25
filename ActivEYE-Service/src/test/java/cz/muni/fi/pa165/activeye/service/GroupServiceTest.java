package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.GroupDao;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   24-11-2016
 */

public class GroupServiceTest {

    @Mock
    private GroupDao groupDao;

    @Inject
    @InjectMocks
    private GroupServiceImpl groupService;

    private Group group;
    private User user1;
    private User user2;
    private List<Group> groups;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this); // Initialise mocks and inject them
    }

    @BeforeMethod
    public void createTestObjects() {
        group = new Group();
        user1 = new User();
        user2 = new User();

        user1.setEmailAddress("Arnnost@lol.com");
        user2.setEmailAddress("Belzebub@lol.com");
        user1.setPasswordHash("password");
        user2.setPasswordHash("password");

        Set<User> users = new HashSet<User>();
        users.add(user1);

        group.setId(0l);
        group.setCreatorsUserId(0l);
        group.setName("Test group 001");
        group.setUsers(users);

        groups = new ArrayList<Group>();
        groups.add(group);
    }

    @BeforeMethod(dependsOnMethods = "createTestObjects")
    public void initMocksBehaviour() {
        when(groupDao.findById(0l)).thenReturn(group);
        when(groupDao.findAll()).thenReturn(groups);
        when(groupDao.isUserInGroup(user1, group)).thenReturn(true);
        when(groupDao.isUserInGroup(user2, group)).thenReturn(false);
        when(groupDao.getAllUsers(group)).thenReturn(new HashSet<User>(){{add(user1);add(user2);}});
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullTest() {
        Mockito.doThrow(new NullPointerException()).when(groupDao).create(null);
        groupService.create(null);
    }

    @Test
    public void testCreate() {
        groupService.create(group);
        verify(groupDao, times(1)).create(group);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullTest() {
        Mockito.doThrow(new NullPointerException()).when(groupDao).update(null);
        groupService.update(null);
    }

    @Test
    public void testUpdate() {
        groupService.update(group);
        verify(groupDao, times(1)).update(group);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullTest() {
        Mockito.doThrow(new NullPointerException()).when(groupDao).delete(null);
        groupService.delete(null);
    }

    @Test
    public void testDelete() {
        groupService.delete(group);
        verify(groupDao, times(1)).delete(group);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByIdNullTest() {
        Mockito.doThrow(new NullPointerException()).when(groupDao).findById(null);
        groupService.findById(null);
    }

    @Test
    public void testFindById() {
        assertDeepEquals(group, groupService.findById(0l));
    }

    @Test
    public void testFindAll() {
        Assert.assertNotNull(groupService.findAll());
        Assert.assertTrue(groupService.findAll().contains(group));
        Assert.assertEquals(groupService.findAll().size(), 1);
    }

    @Test
    public void testIsUserInGroup() {
        Assert.assertTrue(groupService.isUserInGroup(user1, group));
        Assert.assertFalse(groupService.isUserInGroup(user2, group));
    }

    @Test
    public void testGetAllUsers() {
        Assert.assertNotNull(groupService.getAllUsers(group));
        Assert.assertTrue(groupService.getAllUsers(group).contains(user2));
        Assert.assertEquals(groupService.getAllUsers(group).size(), 2);
    }

    @Test
    public void testAddUser() {
        groupService.addUser(user2, group);
        verify(groupDao, times(1)).addUser(user2, group);
    }

    private void assertDeepEquals(Group updated, Group group) {
        Assert.assertEquals(updated, group);
        Assert.assertEquals(updated.getName(), group.getName());
        Assert.assertEquals(updated.getCreatorsUserId(), group.getCreatorsUserId());
        Assert.assertEquals(updated.getUsers(), group.getUsers());
    }

}
