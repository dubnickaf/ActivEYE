package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeMistakeInCalculationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

/**
 * User´s service implementation
 * @author Filip Dubnička [445647]
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private RecordDao recordDao;

    @Override
    public void registerUser(User u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot register inserted null user.");
        }
        if (u.getEmailAddress() == null){
            throw new IllegalArgumentException("Cannot register user with inserted null email address.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot register user with inserted null password.");
        }
        u.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        try {
            userDao.create(u);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public boolean authenticate(User u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot authenticate inserted null user.");
        }
        if (u.getEmailAddress() == null){
            throw new IllegalArgumentException("Cannot register user with inserted null email address.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot authenticate user with inserted null password.");
        }
        return BCrypt.checkpw(password, u.getPasswordHash());
    }

    @Override
    public void updateUser(User u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot update inserted null user.");
        }
        try{
            userDao.update(u);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public void deleteUser(User u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot update inserted null user.");
        }
        if (u.getId() == null) {
            throw new IllegalArgumentException("User has not been successfully persisted yet");
        }
        for (Record record : u.getActivityRecords()){
            try{
                recordDao.deleteRecord(record);
            } catch (Exception e) {
                throw new ActiveyeDataAccessException("Problem on DAO layer",e);
            }
        }
        try{
            userDao.delete(u);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public User findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Cannot find user with null id.");
        }
        try{
           return userDao.findUserById(userId);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find user with null email.");
        }
        try{
            return userDao.findUserByEmail(email);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public Collection<User> getAllUsers() {
        try{
            return userDao.findAll();
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public BigDecimal calculateTotalCaloriesBurned(User user) {
        if(user.getActivityRecords() == null)return null;
        BigDecimal totalCaloriesBurned = BigDecimal.ZERO;
        for (Record record : user.getActivityRecords()) {
            totalCaloriesBurned = totalCaloriesBurned.add(record.getBurnedCalories());
        }
        return totalCaloriesBurned;
    }

    @Override
    public BigDecimal calculateCaloriesBurnedToday(User user) {
        if(user.getActivityRecords() == null)return null;

        BigDecimal caloriesBurnedToday = BigDecimal.ZERO;
        for (Record record : user.getActivityRecords()) {
            if(record.getEndDate().isAfter(getTodaysMidnight())  && record.getEndDate().isBefore(LocalDateTime.now())){
                caloriesBurnedToday = caloriesBurnedToday.add(record.getBurnedCalories());
            }
        }
        return caloriesBurnedToday;
    }

    @Override
    public Integer calculateRecordsToday(User user) {
        if(user.getActivityRecords() == null)return null;
        int recordsToday = 0;
        for (Record record : user.getActivityRecords()) {
            if(record.getEndDate().isAfter(getTodaysMidnight()) && record.getEndDate().isBefore(LocalDateTime.now())){
                recordsToday++;
            }
        }
        return recordsToday;
    }

    @Override
    public BigDecimal calculateAverageBurnedCaloriesPerRecord(User user) {
        if(user.getActivityRecords() == null)return null;
        BigDecimal averageBurnedCaloriesPerRecord = BigDecimal.ZERO;
        return calculateTotalCaloriesBurned(user).divide(new BigDecimal(user.getActivityRecords().size()),BigDecimal.ROUND_UNNECESSARY);
    }

    @Override
    public Integer getTotalRecords(User user) {
        if(user.getActivityRecords() == null)return null;
        return user.getActivityRecords().size();
    }

    @Override
    public Activity calculateMostUsedActivity(User user) {
        if(user.getActivityRecords() == null || getTotalRecords(user) == 0)return null;
        Map<Activity,Integer> activityAndSumOfItsRecords = new HashMap<Activity,Integer>();
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
                return entry.getKey();  // returning first with most, even if there are more activities with same number.
            }
        }
        throw new ActiveyeMistakeInCalculationException("Calculation of most used activity was not successful");
    }

    private static LocalDateTime getTodaysMidnight(){
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    }
}
