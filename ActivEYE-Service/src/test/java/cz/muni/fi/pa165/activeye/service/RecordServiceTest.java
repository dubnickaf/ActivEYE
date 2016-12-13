package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
import org.mockito.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Branislav Bajužík; 445772 on 25.11.16.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.activeye")
public class RecordServiceTest extends AbstractTestNGSpringContextTests {

    private Activity a;
    private User u;
    private Record r;
    private Calendar calS, calE;

    @Mock
    private RecordDao recordDao;

    @Captor
    ArgumentCaptor<Record> aCaptor;

    @Captor
    ArgumentCaptor<Long> lCaptor;

    @Captor
    ArgumentCaptor<String> sCaptor;

    @InjectMocks
    private final RecordService recordService = new RecordServiceImpl();

    @BeforeClass
    public void setupMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void clean() {
        a = new Activity();
        a.setCaloriesRatio(BigDecimal.ONE);
        a.setId(1L);
        a.setName("Quick jog in NY");

        u = new User();
        u.setName("Jet Fuel");
        u.setEmailAddress("cant@melt.steel");
        u.setPasswordHash("beams");
        u.setBornDate(Calendar.getInstance().getTime());
        u.setGender(Gender.MALE);
        u.setId(2L);

        r = new Record();
        calS = Calendar.getInstance();
        calE = Calendar.getInstance();
        r.setActivity(a);
        r.setUser(u);
        calS.set(2001, 9, 11, 8, 46);
        r.setStartDate(calS);
        calE.set(2001, 9, 11, 8, 50);
        r.setEndDate(calE);
        //r.setBurnedCalories(BigDecimal.valueOf(9.11));
    }

    private void asserts() {
        assertThat(aCaptor.getValue().getActivity()).isEqualTo(a);
        assertThat(aCaptor.getValue().getBurnedCalories()).isEqualTo(BigDecimal.valueOf(0.07));
        assertThat(aCaptor.getValue().getEndDate()).isEqualTo(calE);
        assertThat(aCaptor.getValue().getStartDate()).isEqualTo(calS);
        assertThat(aCaptor.getValue().getUser()).isEqualTo(u);
    }

    @Test
    public void create() {
        recordService.createRecord(r);
        Mockito.verify(recordDao).createRecord(aCaptor.capture());

        asserts();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull() {
        recordService.createRecord(null);
    }

    @Test
    public void update() {
        r.setId(3L);

        recordService.updateRecord(r);
        Mockito.verify(recordDao).updateRecord(aCaptor.capture());

        asserts();
        assertThat(aCaptor.getValue().getId()).isEqualTo(3L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull() {
        recordService.updateRecord(null);
    }

    @Test
    public void findById() {
        r.setId(3L);

        when(recordDao.getRecord(3L)).thenReturn(r);

        Record record = recordService.findById(3L);

        assertThat(record.getId()).isEqualTo(3L);
        assertThat(record.getActivity()).isEqualTo(a);
        //assertThat(record.getBurnedCalories()).isEqualTo(BigDecimal.valueOf(9.11));
        assertThat(record.getEndDate()).isEqualTo(calE);
        assertThat(record.getStartDate()).isEqualTo(calS);
        assertThat(record.getUser()).isEqualTo(u);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNullId() {
        recordService.findById(null);
    }

    /*@Test(expectedExceptions = NoSuchEntityFoundException.class)
    public void findByNotUsedId() {
        when(recordDao.getRecord(Long.MAX_VALUE)).thenReturn(null);
        recordService.findById(Long.MAX_VALUE);
    }*/

    @Test
    public void delete() {
        r.setId(3L);

        recordService.deleteRecord(r);
        Mockito.verify(recordDao).deleteRecord(aCaptor.capture());

        assertThat(aCaptor.getValue().getId()).isEqualTo(3L);
        assertThat(aCaptor.getValue().getActivity()).isEqualTo(a);
        //assertThat(aCaptor.getValue().getBurnedCalories()).isEqualTo(BigDecimal.valueOf(0.07));
        assertThat(aCaptor.getValue().getEndDate()).isEqualTo(calE);
        assertThat(aCaptor.getValue().getStartDate()).isEqualTo(calS);
        assertThat(aCaptor.getValue().getUser()).isEqualTo(u);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull() {
        recordService.deleteRecord(null);
    }

    @Test
    public void getAllRecords(){
        List<Record> records = new ArrayList<>();
        r.setId(3L);
        records.add(r);
        records.add(r2());

        when(recordDao.getAllRecords()).thenReturn(records);

        List<Record> records2 = recordService.getAllRecords();
        assertThat(records2.size()).isEqualTo(records.size());

        for (int i = 0; i < records.size(); i++) {
            Record original = records.get(i);
            Record original2 = records2.get(i);

            assertThat(original.getId()).isEqualTo(original2.getId());
            assertThat(original.getActivity()).isEqualTo(original2.getActivity());
            assertThat(original.getBurnedCalories()).isEqualTo(original2.getBurnedCalories());
            assertThat(original.getEndDate()).isEqualTo(original2.getEndDate());
            assertThat(original.getStartDate()).isEqualTo(original2.getStartDate());
            assertThat(original.getUser()).isEqualTo(original2.getUser());
        }
    }

    private Record r2() {
        Activity a = new Activity();
        a.setCaloriesRatio(BigDecimal.ONE);
        a.setId(4L);
        a.setName("Quick jog in NY ");

        User u = new User();
        u.setName("Jet Fuel ");
        u.setEmailAddress("cant_@melt.steel");
        u.setPasswordHash("beams ");
        u.setBornDate(Calendar.getInstance().getTime());
        u.setGender(Gender.FEMALE);
        u.setId(5L);

        Record r = new Record();
        calS = Calendar.getInstance();
        calE = Calendar.getInstance();
        r.setActivity(a);
        r.setUser(u);
        calS.set(2002, 9, 11, 8, 46);
        r.setStartDate(calS);
        calE.set(2002, 9, 11, 8, 50);
        r.setEndDate(calE);
        //r.setBurnedCalories(BigDecimal.valueOf(11.9));
        r.setId(6L);
        return r;
    }
}

