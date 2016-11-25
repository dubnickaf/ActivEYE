package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.config.ServiceConfiguration;
import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingServiceImpl;
import cz.muni.fi.pa165.activeye.service.UserService;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

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
        Assert.assertEquals(userById.getEmailAddress(),jankoEmailAdress);
        Assert.assertEquals(userById.getName(),jankoName);
        Assert.assertEquals(userById.getId(),jankoId);
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




}
