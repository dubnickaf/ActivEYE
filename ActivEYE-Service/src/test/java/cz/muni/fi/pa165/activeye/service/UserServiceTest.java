package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by spriadka on 11/25/16.
 * @author spriadka 
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    private final Long userId = 48L;
    private final String userName = "Donald Trump";
    private final String userEmail = "president@gov.usa";
    private final String userPassword = "InLoveWithMexico";


    private User trump;


    @Mock
    private UserDao userDao;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @BeforeClass
    public void initMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUpTrump(){
        trump = new User();
        trump.setId(userId);
        trump.setName(userName);
        trump.setEmailAddress(userEmail);
    }

    @Test
    public void testCreate(){
        userService.registerUser(trump,userPassword);
        Mockito.verify(userDao).create(trump);
    }

    @Test
    public void testUpdate(){
        when(userDao.findUserById(userId)).thenReturn(trump);
        User fromDb = userService.findUserById(userId);
        fromDb.setGender(Gender.MALE);
        Assert.assertEquals(userId,fromDb.getId());
        Assert.assertEquals(userEmail,fromDb.getEmailAddress());
        Assert.assertEquals(userName,fromDb.getName());
        Assert.assertEquals(Gender.MALE,fromDb.getGender());
    }

    @Test
    public  void testFindById(){
        when(userDao.findUserById(userId)).thenReturn(trump);
        Assert.assertEquals(trump,userService.findUserById(userId));
    }

    @Test
    public void testFindByEmail(){
        when(userDao.findUserByEmail(userEmail)).thenReturn(trump);
        Assert.assertEquals(trump,userService.findUserByEmail(userEmail));
    }

    @Test
    public void testGetAllUsers(){
        User hillary = new User();
        hillary.setName("Hillary Clinton");
        hillary.setEmailAddress("loser@usa.gov");
        hillary.setId(2L);
        User jeb = new User();
        jeb.setName("Jeb Bush");
        jeb.setId(4L);
        jeb.setEmailAddress("jeb@bush.com");
        List<User> allUsers = Arrays.asList(trump,hillary,jeb);
        when(userDao.findAll()).thenReturn(allUsers);
        List<User> fromService = new ArrayList<User>(userService.getAllUsers());
        Assert.assertEquals(3,fromService.size());
        for (int i = 0; i < 3; i++){
            Assert.assertNotNull(fromService.get(i));
            Assert.assertEquals(fromService.get(i),allUsers.get(i));
        }

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull(){
        userService.registerUser(null,"0000");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateWithNullPassword(){
        userService.registerUser(trump,null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull(){
        userService.updateUser(null);
    }

}