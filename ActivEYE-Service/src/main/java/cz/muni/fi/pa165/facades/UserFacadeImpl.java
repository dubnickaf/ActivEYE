package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.activeye.dto.ActivityDTO;
import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.StatisticsOfUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFound;
import cz.muni.fi.pa165.activeye.facades.UserFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.RecordService;
import cz.muni.fi.pa165.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
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
        User user = beanMappingService.mapTo(u, User.class);
        userService.registerUser(user, password);
        u.setId(user.getId());
    }

    @Override
    public boolean authenticate(NotAuthenticatedUserDTO u) {
        User user = userService.findUserByEmail(u.getEmail());
        return userService.authenticate(user, u.getPassword());
    }

    @Override
    public void updateUser(UserDTO u) {
        User user = beanMappingService.mapTo(u, User.class);
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(UserDTO u) {
        User user = beanMappingService.mapTo(u, User.class);
        userService.deleteUser(user);
    }

    @Override
    public UserDTO findUserById(Long userId) {
        User user = userService.findUserById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        Collection<User> users = userService.getAllUsers();
        return (users == null) ? null : beanMappingService.mapTo(users, UserDTO.class);
    }

    @Override
    public StatisticsOfUserDTO getStatistics(UserDTO u) {
        if (u == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }
        User user = userService.findUserById(u.getId());
        if (user == null) {
            throw new NoSuchEntityFound("User does not exist");
        }
        StatisticsOfUserDTO statisticsDTO = new StatisticsOfUserDTO();
        statisticsDTO.setUserDTO(beanMappingService.mapTo(user, UserDTO.class));

        if (user.getActivityRecords() != null || user.getActivityRecords().size() != 0) {


            BigDecimal totalCaloriesBurned = BigDecimal.ZERO;
            for (Record record : user.getActivityRecords()) {
                totalCaloriesBurned.add(record.getBurnedCalories());
            }
            statisticsDTO.setTotalCaloriesBurned(totalCaloriesBurned);



            Calendar todaysMidnight =new GregorianCalendar();
            // reset hour, minutes, seconds and millis
            todaysMidnight.set(Calendar.HOUR_OF_DAY, 0);
            todaysMidnight.set(Calendar.MINUTE, 0);
            todaysMidnight.set(Calendar.SECOND, 0);
            todaysMidnight.set(Calendar.MILLISECOND, 0);

            Calendar now = new GregorianCalendar();

            BigDecimal caloriesBurnedToday = BigDecimal.ZERO;
            int recordsToday = 0;
            for (Record record : user.getActivityRecords()) {
                if(record.getEndDate().after(todaysMidnight) && record.getEndDate().before(now)){
                    totalCaloriesBurned.add(record.getBurnedCalories());
                    recordsToday++;
                }
            }
            statisticsDTO.setTotalCaloriesBurned(caloriesBurnedToday);
            statisticsDTO.setRecordsToday(recordsToday);


            BigDecimal averageBurnedCaloriesPerRecord = BigDecimal.ZERO;
            for (Record record : user.getActivityRecords()) {
                totalCaloriesBurned.add(record.getBurnedCalories());
            }
            averageBurnedCaloriesPerRecord.divide(new BigDecimal(user.getActivityRecords().size()));
            statisticsDTO.setTotalCaloriesBurned(averageBurnedCaloriesPerRecord);

            statisticsDTO.setTotalRecords(user.getActivityRecords().size());


            Map<Activity,Integer> activityAndSumOfItsRecords = new HashMap();
            for (Record record : user.getActivityRecords()) {
                Activity activity = record.getActivity();
                if (activityAndSumOfItsRecords.containsKey(activity)){
                    int oldNum = activityAndSumOfItsRecords.get(activity);
                    activityAndSumOfItsRecords.remove(activity);
                    activityAndSumOfItsRecords.put(activity,oldNum + 1);
                } else {
                    activityAndSumOfItsRecords.put(activity, 1);
                }
            }
            int maxValueInMap=(Collections.max(activityAndSumOfItsRecords.values()));  // This will return max value in the Hashmap
            for (Map.Entry<Activity, Integer> entry : activityAndSumOfItsRecords.entrySet()) {  // Itrate through hashmap
                if (entry.getValue()==maxValueInMap) {
                    ActivityDTO favActivity = beanMappingService.mapTo(entry.getKey(), ActivityDTO.class);
                    statisticsDTO.setMostUsedActivity(favActivity);
                    break;
                }
            }
        } else {
            statisticsDTO.setTotalRecords(0);
            statisticsDTO.setRecordsToday(0);
        }


        return statisticsDTO;
    }
}
