package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dao.ActivityDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
import org.mockito.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Tests for ActivityService
 * @author Filip Dubnička [445647]
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.activeye")
public class ActivityServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ActivityDao activityDao;

    @Captor
    ArgumentCaptor<Activity> argumentCaptor;

    @Captor
    ArgumentCaptor<Long> longCaptor;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @InjectMocks
    private final ActivityService activityService = new ActivityServiceImpl();

    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateActivity(){

        Activity activity = new Activity();
        String name = "dancing";
        activity.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activity.setCaloriesRatio(ratio);

        activityService.create(activity);
        Mockito.verify(activityDao).create(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(argumentCaptor.getValue().getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullActivity() {
        activityService.create(null);
    }

    @Test
    public void testUpdateActivity(){
        Activity activity = new Activity();
        Long id = 1L;
        activity.setId(id);
        String name = "dancing";
        activity.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activity.setCaloriesRatio(ratio);

        activityService.update(activity);
        Mockito.verify(activityDao).update(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getId()).isEqualTo(id);
        assertThat(argumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(argumentCaptor.getValue().getCaloriesRatio()).isEqualTo(ratio);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullActivityTest() {
        activityService.update(null);
    }

    @Test
    public void testFindActivityById(){
        Activity activity = new Activity();
        Long id = 1L;
        activity.setId(id);
        String name = "dancing";
        activity.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activity.setCaloriesRatio(ratio);

        when(activityDao.findById(1L)).thenReturn(activity);

        Activity output = activityService.findById(id);

        assertThat(output.getId()).isEqualTo(id);
        assertThat(output.getName()).isEqualTo(name);
        assertThat(output.getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullIdActivity() {
        activityService.findById(null);
    }

    @Test(expectedExceptions = NoSuchEntityFoundException.class)
    public void testFindActivityByIdNotUsed() {
        when(activityDao.findById(Long.MAX_VALUE)).thenReturn(null);
        activityService.findById(Long.MAX_VALUE);
    }

    @Test
    public void testDeleteActivity(){
        Long id = 1L;
        String name = "dancing";
        BigDecimal ratio = BigDecimal.valueOf(20);

        Activity activity = new Activity();
        activity.setId(id);
        activity.setName(name);
        activity.setCaloriesRatio(ratio);

        activityService.delete(activity);

        Mockito.verify(activityDao).delete(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getId()).isEqualTo(id);
        assertThat(argumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(argumentCaptor.getValue().getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNullActivity() {
        activityService.delete(null);
    }

    @Test
    public void testFindActivityByName(){
        Activity activity = new Activity();
        Long id = 1L;
        activity.setId(id);
        String name = "dancing";
        activity.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activity.setCaloriesRatio(ratio);

        when(activityDao.findByName("dancing")).thenReturn(activity);

        Activity output = activityService.findByName(name);

        assertThat(output.getId()).isEqualTo(id);
        assertThat(output.getName()).isEqualTo(name);
        assertThat(output.getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindActivityByNullName() {
        activityService.findByName(null);
    }

    @Test(expectedExceptions = NoSuchEntityFoundException.class)
    public void testFindActivityByNameNotUsed() {
        when(activityDao.findByName("###&&&###gččč")).thenReturn(null);
        activityService.findByName("###&&&###gččč");
    }

    @Test
    public void testFindAllActivities(){
        List<Activity> activities = exampleActivities();
        when(activityDao.findAll()).thenReturn(activities);

        List<Activity> output = activityService.findAll();
        assertThat(output.size()).isEqualTo(activities.size());

        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            Activity out = output.get(i);
            assertThat(out.getId()).isEqualTo(activity.getId());
            assertThat(out.getName()).isEqualTo(activity.getName());
            assertThat(out.getCaloriesRatio()).isEqualTo(activity.getCaloriesRatio());
        }
    }
/*
    @Test
    public void testFindAllOnEmptyUsed() {
        when(activityDao.findAll()).thenReturn(null);
        List<Activity> list = activityService.findAll();
        assertThat(list).isNotNull();
        assertThat(list).isEmpty();//očakavam prazdnu
    }
*/
    private List<Activity> exampleActivities() {
        Activity swimming = new Activity();
        swimming.setName("swimming");
        swimming.setCaloriesRatio(BigDecimal.valueOf(10));
        swimming.setId(1L);

        Activity squatting = new Activity();
        squatting.setName("squatting");
        squatting.setCaloriesRatio(BigDecimal.valueOf(5));
        squatting.setId(2L);

        Activity running = new Activity();
        running.setName("running");
        running.setCaloriesRatio(BigDecimal.valueOf(2.5));
        running.setId(3L);

        Activity jumping = new Activity();
        jumping.setName("jumping");
        jumping.setCaloriesRatio(BigDecimal.valueOf(1.25));
        jumping.setId(4L);

        List<Activity> activities = new ArrayList<Activity>();
        activities.add(swimming);
        activities.add(squatting);
        activities.add(running);
        activities.add(jumping);
        return activities;
    }

}
