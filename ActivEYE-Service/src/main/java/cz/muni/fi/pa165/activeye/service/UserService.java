package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * User´s service
 * @author Filip Dubnička [445647]
 */
@Service
public interface UserService {
    /**
     * This method registers the given user with given password
     * @param u user to be registered to app
     * @param password user´s chosen password (unencrypted)
     */
    void registerUser(User u, String password);

    /**
     * This method authenticates a user.
     * @param u user to be authenticated
     * @param password user´s version of password from input(unencrypted)
     * @return true on success
     */
    boolean authenticate(User u, String password);

    /**
     * Updates user with attribute check.
     * @param u user to be updated
     */
    void updateUser(User u);

    /**
     * Deletes user.
     * @param u user to be deleted
     */
    void deleteUser(User u);

    /**
     * Returns User with given id
     * @param userId given id
     * @return found user on success, null otherwise
     */
    User findUserById(Long userId);

    /**
     * Returns User with given email
     * @param email given email
     * @return found user on success, null otherwise
     */
    User findUserByEmail(String email);

    /**
     * This method returns all users of Application
     * @return Collection of all users of a app
     */
    Collection<User> getAllUsers();

    /**
     * This method calculates total calories burned for user
     * @param user User to calculate for
     * @return total calories burned
     */
    BigDecimal calculateTotalCaloriesBurned(User user);

    /**
     * This method calculates sum of calories burned today for user
     * @param user User to calculate for
     * @return sum of calories burned today
     */
    BigDecimal calculateCaloriesBurnedToday(User user);

    /**
     * This method calculates number of records today for user
     * @param user User to calculate for
     * @return number of records today
     */
    Integer calculateRecordsToday(User user);

    /**
     * This method calculates average burned calories per record for user
     * @param user User to calculate for
     * @return average burned calories per record
     */
    BigDecimal calculateAverageBurnedCaloriesPerRecord(User user);

    /**
     * This method calculates number of records for user
     * @param user User to calculate for
     * @return number of records
     */
    Integer getTotalRecords(User user);

    /**
     * This method calculates users most used activity
     * @param user User to calculate for
     * @return most used activity
     * @throws cz.muni.fi.pa165.activeye.exceptions.ActiveyeMistakeInCalculationException if there is problem with calculation
     */
    Activity calculateMostUsedActivity(User user);
}
