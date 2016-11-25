package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dto.ActivityDTO;
import cz.muni.fi.pa165.activeye.dto.RecordDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.activeye.service.RecordService;
import org.mockito.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.activeye")
public class RecordFacadeTest {

    private ActivityDTO a;
    private UserDTO u;
    private RecordDTO r;
    private Calendar cal;

    @Mock
    private RecordService recordService;

    @InjectMocks
    private final RecordFacade recordFacade = new RecordFacadeImpl();

    @Captor
    ArgumentCaptor<Record> aCaptor;

    @Captor
    ArgumentCaptor<Long> lCaptor;

    @Spy
    @Inject
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void clean() {
        a = new ActivityDTO();
        a.setCaloriesRatio(BigDecimal.ONE);
        a.setId(1L);
        a.setName("Quick jog in NY");

        u = new UserDTO();
        u.setName("Jet Fuel");
        u.setEmailAddress("cant@melt.steel");
        u.setPasswordHash("beams");
        u.setBornDate(Calendar.getInstance().getTime());
        u.setGender(Gender.MALE);
        u.setId(2L);

        r = new RecordDTO();
        cal = Calendar.getInstance();
        r.setActivity(a);
        r.setUser(u);
        cal.set(2001, 9, 11, 8, 46);
        r.setStartDate(cal);
        cal.set(2001, 9, 11, 8, 50);
        r.setEndDate(cal);
        r.setBurnedCalories(BigDecimal.valueOf(9.11));
    }

    private void asserts() {
        assertThat(aCaptor.getValue().getActivity()).isEqualTo(a);
        assertThat(aCaptor.getValue().getBurnedCalories()).isEqualTo(BigDecimal.valueOf(9.11));
        assertThat(aCaptor.getValue().getEndDate()).isEqualTo(cal);
        cal.set(2001, 9, 11, 8, 46);
        assertThat(aCaptor.getValue().getStartDate()).isEqualTo(cal);
        assertThat(aCaptor.getValue().getUser()).isEqualTo(u);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull() {
        recordFacade.createRecord(null);
    }

    /*@Test
    public void create(){
        recordFacade.createRecord(r);
        Mockito.verify(recordService).createRecord(aCaptor.capture());

        asserts();
    }

    @Test
    public void update(){
        r.setId(3L);

        recordFacade.updateRecord(r);
        Mockito.verify(recordService).updateRecord(aCaptor.capture());

        assertThat(aCaptor.getValue().getId()).isEqualTo(3L);
        asserts();
    }

    @Test
    public void findById(){
        Record rec = beanMappingService.mapTo(r, Record.class);
        rec.setId(4L);

        when(recordService.findById(4L)).thenReturn(rec);

        RecordDTO recordDTO = recordFacade.findById(4L);

        assertThat(recordDTO.getId()).isEqualTo(4L);
        assertThat(recordDTO.getUser()).isEqualTo(u);
        assertThat(recordDTO.getEndDate()).isEqualTo(cal);
        cal.set(2001, 9, 11, 8, 46);
        assertThat(recordDTO.getStartDate()).isEqualTo(cal);
        assertThat(recordDTO.getBurnedCalories()).isEqualTo(BigDecimal.valueOf(9.11));
        assertThat(recordDTO.getActivity()).isEqualTo(a);
    }

    @Test
    public void delete(){
        r.setId(4L);
        Record rec = beanMappingService.mapTo(r, Record.class);

        when(recordService.findById(4L)).thenReturn(rec);

        recordFacade.deleteRecord(r);

        Mockito.verify(recordService).findById(lCaptor.capture());
        assertThat(lCaptor.getValue()).isEqualTo(4L);
        Mockito.verify(recordService).deleteRecord(aCaptor.capture());

        assertThat(aCaptor.getValue().getId()).isEqualTo(4L);
        asserts();
    }*/
}
