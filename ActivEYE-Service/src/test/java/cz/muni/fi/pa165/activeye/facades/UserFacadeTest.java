package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dto.*;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.service.UserService;
import org.assertj.core.api.Assertions;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

/**
 * @author spriadka
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests{

    private final Long jankoId = 1L;
    private final String jankoName = "Janko";
    private final String jankoEmailAdress = "janko@gmail.com";
    private final String jankoPasswd = "passwdPowa";

    @Spy
    @Inject
    private BeanMappingService beanMappingService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFacade userFacade = new UserFacadeImpl();

    private User user;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(jankoId);
        userDTO.setEmailAddress(jankoEmailAdress);
        userDTO.setName(jankoName);
        user = beanMappingService.mapTo(userDTO,User.class);
        userFacade.registerUser(userDTO,jankoPasswd);
        Mockito.verify(userService).registerUser(user,jankoPasswd);
    }

    @Test
    public void testUpdateUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(jankoId);
        userDTO.setName(jankoName);
        userDTO.setEmailAddress("newOne@update.com");
        userFacade.updateUser(userDTO);
        user = beanMappingService.mapTo(userDTO,User.class);
        Mockito.verify(userService).updateUser(user);
        Assert.assertEquals(user.getName(),jankoName);
        Assert.assertEquals(user.getId(),jankoId);
        Assert.assertEquals(user.getEmailAddress(),"newOne@update.com");
    }

    @Test
    public void testFindById(){
        UserDTO userById = new UserDTO();
        userById.setId(jankoId);
        userById.setEmailAddress(jankoEmailAdress);
        userById.setName(jankoName);
        User toReturn = beanMappingService.mapTo(userById,User.class);
        when(userService.findUserById(jankoId)).thenReturn(toReturn);
        UserDTO fromFacade = userFacade.findUserByEmail(jankoEmailAdress);
        Assert.assertEquals(fromFacade.getEmailAddress(),jankoEmailAdress);
        Assert.assertEquals(fromFacade.getName(),jankoName);
        Assert.assertEquals(fromFacade.getId(),jankoId);
    }

    @Test
    public void testFindByEmail(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(jankoId);
        userDTO.setEmailAddress(jankoEmailAdress);
        userDTO.setName(jankoName);
        User shouldReturn = beanMappingService.mapTo(userDTO,User.class);
        when(userService.findUserByEmail(jankoEmailAdress)).thenReturn(shouldReturn);
        UserDTO fromFacade = userFacade.findUserByEmail(jankoEmailAdress);
        Assert.assertEquals(beanMappingService.mapTo(fromFacade,User.class),shouldReturn);

    }

    @Test
    public void testFindAll(){
        User martin = new User();
        martin.setId(2L);
        martin.setName("Martin");
        martin.setEmailAddress("m@m.com");
        User trump = new User();
        trump.setId(3L);
        trump.setName("Trump");
        trump.setEmailAddress("trump@ftw.com");
        user.setId(jankoId);
        user.setName(jankoName);
        user.setEmailAddress(jankoEmailAdress);
        List<User> allUsers = Arrays.asList(martin,trump,user);
        when(userService.getAllUsers()).thenReturn(allUsers);
        List<UserDTO> fromfacadeDTOs = new ArrayList<UserDTO>(userFacade.getAllUsers());
        List<User> fromfacade = beanMappingService.mapTo(fromfacadeDTOs,User.class);
        Assert.assertEquals(3,fromfacade.size());
        Assert.assertEquals(fromfacade.get(0),martin);
        Assert.assertEquals(fromfacade.get(1),trump);
        Assert.assertEquals(fromfacade.get(2),user);

    }

    @Test
    public void testDeleteUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailAddress(jankoEmailAdress);
        userDTO.setId(jankoId);
        userDTO.setName(jankoName);
        userFacade.deleteUser(userDTO);
        user = beanMappingService.mapTo(userDTO,User.class);
        userService.deleteUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterNullUser(){
        userFacade.registerUser(null,"sample");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterUserWithNullPassword(){
        userFacade.registerUser(new UserDTO(),null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRegisterNullEmail(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(22L);
        userDTO.setName(jankoName);
        userFacade.registerUser(userDTO,"0000");
    }

    @Test
    public void testUserStatistics(){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(22L);
        userDTO.setEmailAddress("johny@john.com");
        StatisticsOfUserDTO statisticsOfUserDTO = new StatisticsOfUserDTO();
        statisticsOfUserDTO.setUserDTO(userDTO);
        statisticsOfUserDTO.setRecordsToday(1);
        statisticsOfUserDTO.setCaloriesBurnedToday(new BigDecimal("0"));
        statisticsOfUserDTO.setAverageBurnedCaloriesPerRecord(new BigDecimal("0"));
        
        Activity activity = new Activity();
        activity.setName("sport");
        ActivityDTO favActivity = beanMappingService.mapTo(activity, ActivityDTO.class);
        statisticsOfUserDTO.setMostUsedActivity(favActivity);

        
        User user = beanMappingService.mapTo(userDTO,User.class);
        when(userService.findUserById(22L)).thenReturn(user);
        when(userService.calculateAverageBurnedCaloriesPerRecord(user)).thenReturn(new BigDecimal("0"));
        when(userService.calculateCaloriesBurnedToday(user)).thenReturn(new BigDecimal("0"));
        when(userService.calculateRecordsToday(user)).thenReturn(1);
        when(userService.calculateMostUsedActivity(user)).thenReturn(activity);
        Assert.assertEquals(userFacade.getStatistics(userDTO),statisticsOfUserDTO);

    }

    @Test
    public void testFindWithRecordsById(){
        UserWithRecordsDTO userById = new UserWithRecordsDTO();
        userById.setId(jankoId);
        userById.setEmailAddress(jankoEmailAdress);
        userById.setName(jankoName);
        Set<RecordDTO> records = new HashSet<>();
        records.add(getRecord());
        userById.setActivityRecords(records);
        User toReturn = beanMappingService.mapTo(userById,User.class);
        when(userService.findUserById(jankoId)).thenReturn(toReturn);
        UserWithRecordsDTO returnedValue = userFacade.findUserWithRecordsById(jankoId);
        Assert.assertEquals(returnedValue.getEmailAddress(),jankoEmailAdress);
        Assert.assertEquals(returnedValue.getName(),jankoName);
        Assert.assertEquals(returnedValue.getId(),jankoId);
        Assert.assertEquals(returnedValue.getActivityRecords(),records);
    }

    @Test
    public void testFindWithRecordsByEmail(){
        UserWithRecordsDTO userDTO = new UserWithRecordsDTO();
        userDTO.setId(jankoId);
        userDTO.setEmailAddress(jankoEmailAdress);
        userDTO.setName(jankoName);
        Set<RecordDTO> records = new HashSet<>();
        records.add(getRecord());
        userDTO.setActivityRecords(records);
        User shouldReturn = beanMappingService.mapTo(userDTO,User.class);
        when(userService.findUserByEmail(jankoEmailAdress)).thenReturn(shouldReturn);
        UserWithRecordsDTO fromFacade = userFacade.findUserWithRecordsByEmail(jankoEmailAdress);
        Assert.assertEquals(fromFacade.getEmailAddress(),jankoEmailAdress);
        Assert.assertEquals(fromFacade.getName(),jankoName);
        Assert.assertEquals(fromFacade.getId(),jankoId);
        Assertions.assertThat(fromFacade.getActivityRecords()).containsExactlyElementsOf(records);
    }
    private RecordDTO getRecord() {
        int i;
        ActivityDTO a = new ActivityDTO();
        a.setCaloriesRatio(BigDecimal.ONE);
        a.setId(4L);
        a.setName("Quick jog in NY ");

        UserDTO u = new UserDTO();
        u.setName("Jet Fuel ");
        u.setEmailAddress("cant_@melt.steel");
        u.setPasswordHash("beams ");
        u.setBornDate(LocalDate.now());
        u.setGender(Gender.FEMALE);
        u.setId(5L);

        RecordDTO r = new RecordDTO();
        r.setActivity(a);
        r.setUser(u);
        r.setStartDate(LocalDateTime.now().withYear(2002).withMonth(9).withHour(8).withMinute(46));
        r.setEndDate(LocalDateTime.now().withYear(2001).withMonth(9).withHour(8).withMinute(50));
        r.setBurnedCalories(BigDecimal.valueOf(11.9));
        r.setId(6L);
        return r;
    }
}
