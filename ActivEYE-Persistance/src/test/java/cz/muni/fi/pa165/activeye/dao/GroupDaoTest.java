package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.activeye.PersistenceContext;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.PersistenceException;
import java.util.*;



/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author Filip Dubnička [445647]
 */
public class GroupDaoTest {
    private static GroupDao groupDao;
    private static UserDao userDao;
    private static User userMatko;


    @BeforeClass
    public static void init(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class, PersistenceContext.class);
        groupDao = applicationContext.getBean(GroupDao.class);
        userDao = applicationContext.getBean(UserDao.class);
        userMatko = userMatkoBuilder();
    }

    private static User userMatkoBuilder(){
        User user = new User();
        user.setName("Matko");
        user.setEmailAddress("matko@gmail.com");
        user.setPasswordHash("password");
        user.setGender(Gender.MALE);
        user.setRole(UserRole.USER);
        userDao.create(user);
        return user;
    }

    @After
    public void flushRecords(){
        for (Group group : groupDao.findAll()){
            groupDao.delete(group);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullGroup(){
        groupDao.create(null);
    }

    @Test(expected = PersistenceException.class)
    public void testCreateExisting(){
        Group group = new Group();
        group.setName("Fesaci");
        group.addUser(userMatko);
        group.setCreatorsUserId(userMatko.getId());
        groupDao.create(group);
        groupDao.create(group);
    }

    @Test
    public void testCreateOkUser(){
        Group group = new Group();
        group.setName("Futbalisti");
        group.addUser(userMatko);
        group.setCreatorsUserId(userMatko.getId());
        groupDao.create(group);
        assertThat(group).isNotNull();
        assertThat(group.getId()).isNotNull();
        assertThat(group.getName()).isEqualTo("Futbalisti");
        assertThat(group.getGroupSize()).isEqualTo(1);
    }

    @Test
    public void testFindById(){
        Group group = new Group();
        group.setName("Hokejisti");
        group.addUser(userMatko);
        group.setCreatorsUserId(userMatko.getId());
        groupDao.create(group);
        assertThat(groupDao.findById(group.getId())).isEqualToComparingFieldByField(group);
    }

    @Test
    public void testUpdateGroup(){
        Group group = new Group();
        group.setName("Piloti");
        group.addUser(userMatko);
        group.setCreatorsUserId(userMatko.getId());
        groupDao.create(group);
        group.setName("Plavci");
        groupDao.update(group);
        Assertions.assertThat(groupDao.findById(group.getId()).getName()).isEqualTo("Plavci");
    }

    @Test
    public void testDeleteGroup(){
        Group group = new Group();
        group.setName("Hadzanari");
        group.addUser(userMatko);
        group.setCreatorsUserId(userMatko.getId());
        groupDao.create(group);
        groupDao.delete(group);
        assertThat(groupDao.findById(group.getId())).isNull();
    }

    @Test
    public void testFindAllGroups(){
        Group group = new Group();
        group.setName("Bezci");
        group.addUser(userMatko);
        group.setCreatorsUserId(userMatko.getId());
        groupDao.create(group);

        Group group2 = new Group();
        group2.setName("Silaci");
        group2.addUser(userMatko);
        group2.setCreatorsUserId(userMatko.getId());
        groupDao.create(group2);

        assertThat(groupDao.findAll()).contains(group,group2);
    }

    @Test
    public void testIsUserInGroup() {
        User user1 = userMatko;

        User user2 = new User();
        user2.setName("Branko");
        user2.setEmailAddress("branko@gmail.com");
        user2.setPasswordHash("password");
        user2.setGender(Gender.MALE);
        user2.setRole(UserRole.USER);
        userDao.create(user2);

        Group group = new Group();
        group.addUser(user1);
        group.setCreatorsUserId(userMatko.getId());
        group.setName("Programatori");
        Set<User> users = new HashSet<User>();
        users.add(user1);

        group.setUsers(users);
        groupDao.create(group);

        assertThat(groupDao.isUserInGroup(user1,group)).isTrue();
        assertThat(groupDao.isUserInGroup(user2,group)).isFalse();
    }

    @Test
    public void testGetAllUsers() {
        User user1 = userMatko;

        User user2 = new User();
        user2.setName("Ferko");
        user2.setEmailAddress("ferko@gmail.com");
        user2.setPasswordHash("password");
        user2.setGender(Gender.MALE);
        user2.setRole(UserRole.USER);
        userDao.create(user2);

        User user3 = new User();
        user3.setName("Vratko");
        user3.setEmailAddress("vratko@gmail.com");
        user3.setPasswordHash("password");
        user3.setGender(Gender.MALE);
        user3.setRole(UserRole.USER);
        userDao.create(user3);


        Group group = new Group();
        group.setCreatorsUserId(userMatko.getId());
        group.setName("Kavickari");
        Set<User> usersToDB = new HashSet<User>();
        usersToDB.add(user1);
        usersToDB.add(user2);

        group.setUsers(usersToDB);
        groupDao.create(group);

        assertThat(groupDao.getAllUsers(group)).doesNotContain(user3).containsOnly(user1,user2);
    }

    @Test
    public void testAddUser() {
        User user1 = userMatko;

        Group group = new Group();
        group.setCreatorsUserId(userMatko.getId());
        group.setName("Lyziari");
        Set<User> usersToDB = new HashSet<User>();
        group.setUsers(usersToDB);
        groupDao.create(group);

        groupDao.addUser(user1,group);
        assertThat(groupDao.getAllUsers(group)).containsOnly(user1);
    }
}
