package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.activeye.PersistenceContext;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author spriadka
 */
public class RecordDaoTest {

    @BeforeClass
    public static void init(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class, PersistenceContext.class);
        userDao = applicationContext.getBean(UserDao.class);
        activityDao = applicationContext.getBean(ActivityDao.class);
        recordDao = applicationContext.getBean(RecordDao.class);

        User user = new User();
        user.setName("Janko");
        user.setEmailAddress("janko@gmail.com");
        user.setGender(Gender.MALE);
        Calendar birthday = Calendar.getInstance();
        birthday.set(1994,2,2);
        user.setBornDate(birthday.getTime());
        Activity activity = new Activity();
        activity.setName("Beh");
        activity.setCaloriesRatio(new BigDecimal("0.9"));
        userDao.create(user);
        activityDao.create(activity);
    }

    private static UserDao userDao;

    private static ActivityDao activityDao;

    private static RecordDao recordDao;

    @Test
    public void test(){
        Record record = new Record();
        User user = userDao.findUserByEmail("janko@gmail.com");
        Activity activity = activityDao.findByName("Beh");
        long time = Long.valueOf(60000);
        BigDecimal burnedCalories = activity.getCaloriesRatio().multiply(new BigDecimal(time));
        record.setUser(user);
        record.setActivity(activity);
        record.setBurnedCalories(burnedCalories);
        record.setTime(time);
        recordDao.createRecord(record);
        Assert.assertTrue(record != null);
        Assert.assertTrue(record.getId() != null);
        Assert.assertEquals(user,record.getUser());
        Assert.assertEquals(activity,record.getActivity());
        Assert.assertEquals(burnedCalories,record.getBurnedCalories());
    }

}
