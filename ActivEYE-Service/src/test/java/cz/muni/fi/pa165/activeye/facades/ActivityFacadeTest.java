package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.ActivityDTO;
import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.activeye.service.ActivityService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for ActivityFacade
 * @author Filip Dubnička [445647]
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.activeye")
public class ActivityFacadeTest extends AbstractTestNGSpringContextTests {


    private Activity swimming;

    private Activity squatting;

    private Activity running;

    private Activity jumping;

    @Mock
    private ActivityService activityService;

    @Captor
    ArgumentCaptor<Activity> argumentCaptor;

    @Captor
    ArgumentCaptor<Long> longCaptor;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @InjectMocks
    private final ActivityFacade activityFacade = new ActivityFacadeImpl();

    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateActivity(){

        ActivityDTO activityDTO = new ActivityDTO();
        String name = "dancing";
        activityDTO.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activityDTO.setCaloriesRatio(ratio);

        activityFacade.createActivity(activityDTO);
        Mockito.verify(activityService).create(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(argumentCaptor.getValue().getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullActivityTest() {
        activityFacade.createActivity(null);
    }

    @Test
    public void testUpdateActivity(){
        ActivityDTO activityDTO = new ActivityDTO();
        Long id = 1L;
        activityDTO.setId(id);
        String name = "dancing";
        activityDTO.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activityDTO.setCaloriesRatio(ratio);

        activityFacade.updateActivity(activityDTO);
        Mockito.verify(activityService).update(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getId()).isEqualTo(id);
        assertThat(argumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(argumentCaptor.getValue().getCaloriesRatio()).isEqualTo(ratio);

    }

    @Test
    public void testFindById(){
        Activity activity = new Activity();
        Long id = 1L;
        activity.setId(id);
        String name = "dancing";
        activity.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activity.setCaloriesRatio(ratio);

        when(activityService.findById(1l)).thenReturn(activity);

        ActivityDTO activityDTO = activityFacade.findById(id);

        assertThat(activityDTO.getId()).isEqualTo(id);
        assertThat(activityDTO.getName()).isEqualTo(name);
        assertThat(activityDTO.getCaloriesRatio()).isEqualTo(ratio);
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

        when(activityService.findById(1l)).thenReturn(activity);

        activityFacade.deleteActivity(id);

        Mockito.verify(activityService).findById(longCaptor.capture());
        assertThat(longCaptor.getValue()).isEqualTo(id);

        Mockito.verify(activityService).delete(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getId()).isEqualTo(id);
        assertThat(argumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(argumentCaptor.getValue().getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test
    public void testFindByName(){
        Activity activity = new Activity();
        Long id = 1L;
        activity.setId(id);
        String name = "dancing";
        activity.setName(name);
        BigDecimal ratio = BigDecimal.valueOf(20);
        activity.setCaloriesRatio(ratio);

        when(activityService.findByName("dancing")).thenReturn(activity);

        ActivityDTO activityDTO = activityFacade.findByName(name);

        assertThat(activityDTO.getId()).isEqualTo(id);
        assertThat(activityDTO.getName()).isEqualTo(name);
        assertThat(activityDTO.getCaloriesRatio()).isEqualTo(ratio);
    }

    @Test
    public void testFindAll(){
        List<Activity> activities = exampleActivities();
        when(activityService.findAll()).thenReturn(activities);

        List<ActivityDTO> output = activityFacade.findAll();
        assertThat(output.size()).isEqualTo(activities.size());

        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            ActivityDTO out = output.get(i);
            assertThat(out.getId()).isEqualTo(activity.getId());
            assertThat(out.getName()).isEqualTo(activity.getName());
            assertThat(out.getCaloriesRatio()).isEqualTo(activity.getCaloriesRatio());
        }
    }

    private List<Activity> exampleActivities() {
        swimming = new Activity();
        swimming.setName("swimming");
        swimming.setCaloriesRatio(BigDecimal.valueOf(10));
        swimming.setId(1L);

        squatting = new Activity();
        squatting.setName("squatting");
        squatting.setCaloriesRatio(BigDecimal.valueOf(5));
        squatting.setId(2L);

        running = new Activity();
        running.setName("running");
        running.setCaloriesRatio(BigDecimal.valueOf(2.5));
        running.setId(3L);

        jumping = new Activity();
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
