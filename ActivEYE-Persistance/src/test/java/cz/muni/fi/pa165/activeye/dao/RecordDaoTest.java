package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.activeye.PersistenceContext;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   28-10-2016
 */

@Transactional
public class RecordDaoTest {

    private static RecordDao recordDao;

    @BeforeClass
    public static void init(){
        ApplicationContext appContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class, PersistenceContext.class);
        recordDao = appContext.getBean(RecordDao.class);
    }

    @After
    public void flushRecords(){
        for (Record record : recordDao.getAllRecords()){
            recordDao.deleteRecord(record);
        }
    }

    /// CREATE tests
    @Test(expected = IllegalArgumentException.class)
    public void createRecordNull() {
        recordDao.createRecord(null);
    }

    @Test(expected = PersistenceException.class)
    public void createDuplicateRecord() {
        Record record = new Record();
        record.setBurnedCalories(new BigDecimal("14.00"));
        recordDao.createRecord(record);
        recordDao.createRecord(record);
    }

    // RETRIEVE tests
    @Test(expected = IllegalArgumentException.class)
    public void findRecordByIdNull() {
        recordDao.getRecord(null);
    }

    @Test
    public void findRecordById() {
        Record record = new Record();
        record.setBurnedCalories(new BigDecimal("14.15"));
        recordDao.createRecord(record);
        Record find = recordDao.getRecord(record.getId());
        Assert.assertEquals(find.getId(), record.getId());
        Assert.assertEquals(find.getBurnedCalories(), new BigDecimal("14.15"));
    }

    @Test
    public void findRecordByIdNoResult() {
        Assert.assertNull(recordDao.getRecord(999l));
    }

    @Test
    public void findAllRecords() {
        Record r1 = new Record();
        Record r2 = new Record();

        r1.setBurnedCalories(new BigDecimal("14.15"));
        r2.setBurnedCalories(new BigDecimal("99.99"));

        recordDao.createRecord(r1);
        recordDao.createRecord(r2);

        List<Record> records = recordDao.getAllRecords();

        Assert.assertEquals(2, records.size());
    }

    // UPDATE test
    @Test(expected = IllegalArgumentException.class)
    public void updateRecordNull() {
        recordDao.createRecord(null);
    }

    @Test
    public void updateRecord() {
        Record record = new Record();
        record.setBurnedCalories(new BigDecimal("14.15"));
        recordDao.createRecord(record);
        record.setBurnedCalories(new BigDecimal("99.99"));
        recordDao.updateRecord(record);
        Record find = recordDao.getRecord(record.getId());
        Assert.assertEquals(find.getBurnedCalories(), new BigDecimal("99.99"));
    }

    // DELETE tests
    @Test(expected = IllegalArgumentException.class)
    public void deleteRecordNull() {
        recordDao.deleteRecord(null);
    }

    @Test
    public void deleteRecord() {
        Record record = new Record();
        record.setBurnedCalories(new BigDecimal("14.15"));
        recordDao.createRecord(record);
        Assert.assertNotNull(recordDao.getRecord(record.getId()));
        recordDao.deleteRecord(record);
        Assert.assertNull(recordDao.getRecord(record.getId()));
    }

    @Test
    public void equalsRecord() {
        Record record = new Record();
        Activity activity = new Activity();
        User user = new User();

        // compulsory attributes for Activity and User
        activity.setName("name for my activity");
        user.setName("name for my user");
        user.setEmailAddress("e@mail.com");

        record.setActivity(activity);
        record.setUser(user);
        record.setBurnedCalories(new BigDecimal("14.15"));
        record.setTime(15l);

        recordDao.createRecord(record);

        Record find = recordDao.getRecord(record.getId());

        Assert.assertEquals(record, find);
    }

}
