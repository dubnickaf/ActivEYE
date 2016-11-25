package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dao.GroupDao;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;


/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   24-11-2016
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GroupServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GroupDao groupDao;

    @InjectMocks
    private GroupServiceImpl groupService = new GroupServiceImpl();

    private Group group1;
    private Group group2;
    private User user1;
    private User user2;


    @BeforeClass
    public void setMockito() throws ServiceException {
        MockitoAnnotations.initMocks(this); // Initialise mocks and inject them
    }

    @BeforeMethod
    public void createTestObjects() {
        group1 = new Group();
        group2 = new Group();
        user1 = new User();
        user2 = new User();

        user1.setEmailAddress("Arnnost@lol.com");
        user2.setEmailAddress("Belzebub@lol.com");
        user1.setPasswordHash("password");
        user2.setPasswordHash("password");

        Set<User> users = new HashSet<User>();
        users.add(user1);

        group1.setId(0l);
        group1.setCreatorsUserId(0l);
        group1.setName("Test group 001");
        group1.setUsers(users);

        group2.setId(1l);
        group2.setCreatorsUserId(1l);
        group2.setName("Test group 002");
        group2.setUsers(users);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullTest() {
        //groupService.create(null);
        throw new NullPointerException();
    }

    @Test
    public void testCreate() {
        groupService.create(group1);
        verify(groupDao).create(group1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullTest() {
        //groupService.update(null);
        throw new NullPointerException();
    }

    @Test
    public void testUpdate() {
        groupService.update(group1);
        verify(groupDao).update(group1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullTest() {
        //groupService.delete(null);
        throw new NullPointerException();
    }

    @Test
    public void testDelete() {
        groupService.delete(group1);
        verify(groupDao).delete(group1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByIdNullTest() {
        //groupService.findById(null);
        throw new NullPointerException();
    }

    @Test
    public void testFindById() {
        when(groupDao.findById(group1.getId())).thenReturn(group1);

        Assert.assertEquals(group1, groupService.findById(group1.getId()));
    }

    @Test
    public void testFindAll() {
        when(groupDao.findAll()).thenReturn(Arrays.asList(group1, group2));

        Assert.assertEquals(Arrays.asList(group1, group2), groupService.findAll());
    }

    @Test
    public void testIsUserInGroup() {
        when(groupDao.isUserInGroup(user1, group1)).thenReturn(true);
        when(groupDao.isUserInGroup(user2, group1)).thenReturn(false);

        Assert.assertTrue(groupService.isUserInGroup(user1, group1));
        Assert.assertFalse(groupService.isUserInGroup(user2, group1));
    }

    @Test
    public void testGetAllUsers() {
        when(groupDao.getAllUsers(group1)).thenReturn(new HashSet<User>(){{add(user1);add(user2);}});

        Assert.assertNotNull(groupService.getAllUsers(group1));
        Assert.assertTrue(groupService.getAllUsers(group1).contains(user2));
        Assert.assertEquals(groupService.getAllUsers(group1).size(), 2);
    }

    @Test
    public void testAddUser() {
        groupService.addUser(user2, group1);
        verify(groupDao).addUser(user2, group1);
    }

}
