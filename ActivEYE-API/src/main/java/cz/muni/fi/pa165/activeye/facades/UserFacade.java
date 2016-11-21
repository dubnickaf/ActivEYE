package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;

import java.util.Collection;

/**
 * User´s facade
 * @author Filip Dubnička [445647]
 */
public interface UserFacade {

    /**
     * This method registers the given user with given password
     */
    void registerUser(UserDTO u, String password);

    /**
     * This method authenticates a user.
     */
    boolean authenticate(NotAuthenticatedUserDTO u);

    /**
     * Updates user with attribute check.
     */
    void updateUser(UserDTO u);

    /**
     * Deletes user.
     */
    void deleteUser(UserDTO u);

    /**
     * Returns User with given id
     */
    UserDTO findUserById(Long userId);

    /**
     * Returns User with given email
     */
    UserDTO findUserByEmail(String email);

    /**
     * This method returns all users of Application
     */
    Collection<UserDTO> getAllUsers();

}
