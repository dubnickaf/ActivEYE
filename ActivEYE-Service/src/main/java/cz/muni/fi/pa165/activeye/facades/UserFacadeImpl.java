package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.*;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.service.RecordService;
import cz.muni.fi.pa165.activeye.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

/**
 * User´s facade
 *
 * @author Filip Dubnička [445647]
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    UserService userService;

    @Inject
    RecordService recordService;

    @Inject
    BeanMappingService beanMappingService;

    @Override
    public void registerUser(UserDTO u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot register inserted null UserDTO.");
        }
        if (u.getEmailAddress() == null){
            throw new IllegalArgumentException("Cannot register user with inserted null email address.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot register user with inserted null password.");
        }
        User user = beanMappingService.mapTo(u, User.class);
        userService.registerUser(user, password);
        u.setId(user.getId());
    }

    @Override
    public boolean authenticate(NotAuthenticatedUserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot authenticate inserted null NotAuthenticatedUserDTO.");
        }
        if (u.getEmail() == null){
            throw new IllegalArgumentException("Cannot register user with inserted null email address.");
        }
        User user = userService.findUserByEmail(u.getEmail());
        return userService.authenticate(user, u.getPassword());
    }

    @Override
    public void updateUser(UserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot update inserted null UserDTO.");
        }
        User user = beanMappingService.mapTo(u, User.class);
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(UserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot update inserted null user.");
        }
        if (u.getId() == null) {
            throw new IllegalArgumentException("User has not been successfully persisted yet");
        }
        User user = beanMappingService.mapTo(u, User.class);
        userService.deleteUser(user);
    }

    @Override
    public UserDTO findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Cannot find user with null id.");
        }
        User user = userService.findUserById(userId);
        if(user == null) throw new NoSuchEntityFoundException("No user with user id " + userId + " found");
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find user with null email.");
        }
        User user = userService.findUserByEmail(email);
        if(user == null) throw new NoSuchEntityFoundException("No user with email " + email + " found");
        return beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        Collection<User> users = userService.getAllUsers();
        if(users == null) throw new NoSuchEntityFoundException("No users found");
        return beanMappingService.mapTo(users, UserDTO.class);
    }

    @Override
    public StatisticsOfUserDTO getStatistics(UserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User user = userService.findUserById(u.getId());
        if (user == null) {
            throw new NoSuchEntityFoundException("User does not exist");
        }
        StatisticsOfUserDTO statisticsDTO = new StatisticsOfUserDTO();
        statisticsDTO.setUserDTO(beanMappingService.mapTo(user, UserDTO.class));

        statisticsDTO.setTotalCaloriesBurned(userService.calculateTotalCaloriesBurned(user));
        statisticsDTO.setCaloriesBurnedToday(userService.calculateCaloriesBurnedToday(user));
        statisticsDTO.setRecordsToday(userService.calculateRecordsToday(user));
        statisticsDTO.setAverageBurnedCaloriesPerRecord(userService.calculateAverageBurnedCaloriesPerRecord(user));
        statisticsDTO.setTotalRecords(userService.getTotalRecords(user));
        statisticsDTO.setNumberOfGroups(userService.getNumberOfGroups(user));

        ActivityDTO favActivity = beanMappingService.mapTo(userService.calculateMostUsedActivity(user), ActivityDTO.class);
        statisticsDTO.setMostUsedActivity(favActivity);

        return statisticsDTO;
    }

    @Override
    public UserWithRecordsDTO findUserWithRecordsById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Cannot find user with null id.");
        }
        User user = userService.findUserById(userId);
        if(user == null) throw new NoSuchEntityFoundException("No user with user id " + userId + " found");
        return beanMappingService.mapTo(user, UserWithRecordsDTO.class);
    }

    @Override
    public UserWithRecordsDTO findUserWithRecordsByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find user with null email.");
        }
        User user = userService.findUserByEmail(email);
        if(user == null) throw new NoSuchEntityFoundException("No user with email " + email + " found");
        return beanMappingService.mapTo(user, UserWithRecordsDTO.class);
    }
    @Override
    public UserWithGroupsDTO findUserWithGroupsById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Cannot find user with null id.");
        }
        User user = userService.findUserById(userId);
        if(user == null) throw new NoSuchEntityFoundException("No user with user id " + userId + " found");
        return beanMappingService.mapTo(user, UserWithGroupsDTO.class);
    }

    @Override
    public UserWithGroupsDTO findUserWithGroupsByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find user with null email.");
        }
        User user = userService.findUserByEmail(email);
        if(user == null) throw new NoSuchEntityFoundException("No user with email " + email + " found");
        return beanMappingService.mapTo(user, UserWithGroupsDTO.class);
    }
}
