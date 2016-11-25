package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.activeye.PersistenceContext;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author spriadka
 */


public class UserDaoTest {

    private static UserDao userDao;

    @BeforeClass
    public static void init(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class, PersistenceContext.class);
        userDao = applicationContext.getBean(UserDao.class);
    }

    @After
    public void flushRecords(){
        for (User user : userDao.findAll()){
            userDao.delete(user);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullUser(){
        userDao.create(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateExisting(){
        User toCreate = new User();
        toCreate.setName("Jozko");
        toCreate.setEmailAddress("jozko@mail.com");
        toCreate.setPasswordHash("password");
        userDao.create(toCreate);
        userDao.create(toCreate);
    }
    @Test(expected = ConstraintViolationException.class)
    public void testCreateUserWithInvalidEmail(){
        User user = new User();
        user.setName("Martin");
        user.setEmailAddress("lalala");
        user.setPasswordHash("password");
        userDao.create(user);
    }
    @Test(expected = ConstraintViolationException.class)
    public void testCreateUserWithFutureBirthDate(){
        User user = new User();
        user.setName("Jozko");
        user.setEmailAddress("j@j.com");
        user.setPasswordHash("password");
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTimeInMillis(now.getTimeInMillis() + (1000 * 60 * 60 * 24));
        user.setBornDate(tomorrow.getTime());
        userDao.create(user);
    }

    @Test
    public void testCreateOkUser(){
        User user = new User();
        user.setName("Jozko");
        user.setEmailAddress("jozko@gmail.com");
        user.setPasswordHash("password");
        user.setGender(Gender.MALE);
        userDao.create(user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals("Jozko",user.getName());
        Assert.assertEquals("jozko@gmail.com",user.getEmailAddress());
        Assert.assertEquals("password",user.getPasswordHash());
        Assert.assertEquals(Gender.MALE,user.getGender());
        Assert.assertTrue(1 == userDao.findAll().size());
    }

    @Test
    public void testEquals(){
        String name = "Jozko";
        String email = "jozko@jozko.com";
        User user = new User();
        user.setName(name);
        user.setEmailAddress(email);
        user.setPasswordHash("password");
        User sameUser = user;
        userDao.create(user);
        User fromDB = userDao.findUserById(user.getId());
        Assert.assertEquals(fromDB,sameUser);
        fromDB.setEmailAddress("new@testing.com");
        userDao.update(fromDB);
        Assert.assertNotEquals(fromDB,sameUser);
    }
}
