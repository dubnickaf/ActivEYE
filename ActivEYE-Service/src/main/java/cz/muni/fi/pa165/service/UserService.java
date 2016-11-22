package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.entities.User;
import org.springframework.stereotype.Service;

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
}
