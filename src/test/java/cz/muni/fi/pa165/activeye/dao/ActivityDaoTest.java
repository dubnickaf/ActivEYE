package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.InMemoryDatabaseSpring;
import cz.muni.fi.pa165.activeye.PersistenceContext;
import cz.muni.fi.pa165.activeye.entities.Activity;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.fail;

/**
 * @author Branislav Bajuzik; 445772
 */
public class ActivityDaoTest {

    private static ActivityDao aDao;
    private static Activity activity;

    @BeforeClass
    public static void setUpClass() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class, PersistenceContext.class);
        aDao = applicationContext.getBean(ActivityDao.class);
    }

    @Before
    public void setUp() {
        activity = new Activity();
        activity.setName("Sport");
        activity.setCaloriesRatio(BigDecimal.TEN);
    }

    @After
    public  void tearDown() {
        for (Activity a : aDao.findAll()) {
            aDao.delete(a);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCreate() {
        try {
            aDao.create(null);
            fail("Activity can't be null!");
        } catch (IllegalArgumentException ex) { /*ok*/ }
        activity.setName(null);
        activity.setCaloriesRatio(null);
        activity.setId(42L);
        aDao.create(activity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalAttributesCreate() {
        activity.setName("");
        activity.setCaloriesRatio(BigDecimal.ZERO);
        try {
            aDao.create(activity);
            fail("Activity name or caloriesRatio was invalid: " + activity);
        } catch (IllegalArgumentException ex) { /*ok*/ }
        activity.setCaloriesRatio(BigDecimal.valueOf(-42.0));
        aDao.create(activity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicateCreate() {
        aDao.create(activity);
        aDao.create(activity);
    }

    @Test
    public void okCreate() {
        aDao.create(activity);
        Activity activity2 = new Activity();
        activity2.setId(activity.getId());
        activity2.setName("Sport");
        activity2.setCaloriesRatio(BigDecimal.TEN);

        Assert.assertNotNull("Id wasn't set!", activity.getId());
        Assert.assertTrue("Activity was changed after creating!", deepEquals(activity, activity2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullUpdate() {
        try {
            aDao.update(null);
            fail("Activity can't be null");
        } catch (IllegalArgumentException ex) { /*ok*/ }
        activity.setName(null);
        activity.setCaloriesRatio(null);
        activity.setId(null);
        aDao.update(activity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalAttributesUpdate() {
        activity.setName("");
        activity.setCaloriesRatio(BigDecimal.ZERO);
        try {
            aDao.update(activity);
            fail("Activity name or caloriesRatio was invalid: " + activity);
        } catch (IllegalArgumentException ex) { /*ok*/ }
        activity.setCaloriesRatio(BigDecimal.valueOf(-42.0));
        aDao.update(activity);
    }

    @Test
    public void okUpdate() {
        aDao.create(activity);
        activity.setName("Sport2");
        activity.setCaloriesRatio(BigDecimal.ONE);
        aDao.update(activity);

        Activity activity2 = new Activity();
        activity2.setId(activity.getId());
        activity2.setName("Sport2");
        activity2.setCaloriesRatio(BigDecimal.ONE);

        Assert.assertTrue("Activity wasn't changed after updating!", deepEquals(activity, activity2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDelete() {
        try {
            aDao.delete(null);
            fail("Activity cant be null!");
        } catch (IllegalArgumentException ex) { /*ok*/ }
        activity.setName(null);
        activity.setCaloriesRatio(null);
        activity.setId(null);
        aDao.delete(activity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalAttributesDelete() {
        activity.setName("");
        activity.setCaloriesRatio(BigDecimal.ZERO);
        try {
            aDao.delete(activity);
            fail("Activity name or caloriesRatio was invalid: " + activity);
        } catch (IllegalArgumentException ex) { /*ok*/ }
        activity.setCaloriesRatio(BigDecimal.valueOf(-42.0));
        aDao.delete(activity);
    }

    @Test
    public void okDelete() {
        aDao.create(activity);
        Long id = activity.getId();
        aDao.delete(activity);
        Assert.assertNull("Activity was not deleted", aDao.findById(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void FindById() {
        try {
            aDao.findById(null);
            fail("Id can't be null!");
        } catch (IllegalArgumentException ex) { /*ok*/ }
        try {
            aDao.findById(0L);
            fail("Id can't be < 1!");
        } catch (IllegalArgumentException ex) { /*ok*/ }
        aDao.findById(-1L);

        aDao.create(activity);
        Activity activity2 = new Activity();
        activity2.setName("Sport2");
        activity2.setCaloriesRatio(BigDecimal.ONE);
        aDao.create(activity2);

        Assert.assertTrue("Retrieved " + activity + " != " + aDao.findById(activity.getId()) + "!",
                deepEquals(activity, aDao.findById(activity.getId())));
        Assert.assertTrue("Retrieved " + aDao.findById(activity.getId()) + " != " + aDao.findById(activity.getId()) + " retrieved by same id!",
                deepEquals(aDao.findById(activity.getId()), aDao.findById(activity.getId())));
        Assert.assertFalse("Retrieved " + aDao.findById(activity.getId()) + " == " + aDao.findById(activity2.getId()) + " retrieved different id!",
                deepEquals(aDao.findById(activity.getId()), aDao.findById(activity2.getId())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void FindByName() {
        try {
            aDao.findByName(null);
            fail("Name can't be null!");
        } catch (IllegalArgumentException ex) { /*ok*/ }
        aDao.findByName("");

        aDao.create(activity);
        Activity activity2 = new Activity();
        activity2.setName("Sport2");
        activity2.setCaloriesRatio(BigDecimal.ONE);
        aDao.create(activity2);

        Assert.assertTrue("Retrieved " + activity + " != " + aDao.findByName(activity.getName()) + "!",
                deepEquals(activity, aDao.findByName(activity.getName())));
        Assert.assertTrue("Retrieved " + aDao.findByName(activity.getName()) + " != " + aDao.findByName(activity.getName()) + " retrieved by same name!",
                deepEquals(aDao.findByName(activity.getName()), aDao.findByName(activity.getName())));
        Assert.assertFalse("Retrieved " + aDao.findByName(activity.getName()) + " == " + aDao.findByName(activity2.getName()) + " retrieved by different name!",
                deepEquals(aDao.findByName(activity.getName()), aDao.findByName(activity2.getName())));
    }

    @Test
    public void FindAll() {
        aDao.create(activity);
        Activity activity2 = new Activity();
        activity2.setName("Sport2");
        activity2.setCaloriesRatio(BigDecimal.ONE);
        aDao.create(activity2);

        Activity activity3 = new Activity();
        activity3.setName("Sport3");
        activity3.setCaloriesRatio(BigDecimal.valueOf(42));
        aDao.create(activity3);

        List<Activity> expected = new ArrayList<Activity>();
        expected.add(activity);
        expected.add(activity2);
        expected.add(activity3);

        Assert.assertTrue("Retrieved List doesn't contain expected Activities", aDao.findAll().containsAll(expected));
    }

    private boolean deepEquals(Activity a1, Activity a2) {
        boolean ret = a1.equals(a2);
        ret = ret && a1.getId().equals(a2.getId());
        ret = ret && a1.getName().equals(a2.getName());
        return ret && a1.getCaloriesRatio().compareTo(a2.getCaloriesRatio()) == 0;
    }
}
