package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dto.ActivityDTO;
import cz.muni.fi.pa165.activeye.dto.RecordDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.activeye.service.RecordService;
import org.mockito.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
public class RecordFacadeTest extends AbstractTestNGSpringContextTests{

    private ActivityDTO a;
    private UserDTO u;
    private RecordDTO r;
    private Calendar calS, calE;

    @Mock
    private RecordService recordService;

    @InjectMocks
    private final RecordFacade recordFacade = new RecordFacadeImpl();

    @Captor
    ArgumentCaptor<Record> captor;

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
        calS = Calendar.getInstance();
        calE = Calendar.getInstance();
        r.setActivity(a);
        r.setUser(u);
        calS.set(2001, 9, 11, 8, 46);
        r.setStartDate(calS);
        calE.set(2001, 9, 11, 8, 50);
        r.setEndDate(calE);
        r.setBurnedCalories(BigDecimal.valueOf(9.11));
    }

    private void asserts() {
        assertThat(captor.getValue().getActivity()).isEqualTo(beanMappingService.mapTo(a, Activity.class));
        assertThat(captor.getValue().getBurnedCalories()).isEqualTo(BigDecimal.valueOf(9.11));
        assertThat(captor.getValue().getEndDate()).isEqualTo(calE);
        assertThat(captor.getValue().getStartDate()).isEqualTo(calS);
        assertThat(captor.getValue().getUser()).isEqualTo(beanMappingService.mapTo(u, User.class));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull() {
        recordFacade.createRecord(null);
    }

    @Test
    public void create(){
        recordFacade.createRecord(r);
        Mockito.verify(recordService).createRecord(captor.capture());

        asserts();
    }

    @Test
    public void update(){
        r.setId(3L);

        recordFacade.updateRecord(r);
        Mockito.verify(recordService).updateRecord(captor.capture());

        assertThat(captor.getValue().getId()).isEqualTo(3L);
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
        assertThat(recordDTO.getEndDate()).isEqualTo(calE);
        assertThat(recordDTO.getStartDate()).isEqualTo(calS);
        assertThat(recordDTO.getBurnedCalories()).isEqualTo(BigDecimal.valueOf(9.11));
        assertThat(recordDTO.getActivity()).isEqualTo(a);
    }

    @Test
    public void delete(){
        r.setId(4L);
        Record rec = beanMappingService.mapTo(r, Record.class);

        when(recordService.findById(4L)).thenReturn(rec);

        recordFacade.deleteRecord(r);

        Mockito.verify(recordService).deleteRecord(captor.capture());

        assertThat(captor.getValue().getId()).isEqualTo(4L);
        asserts();
    }

}
