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
     * @param u DTO of user to be registered to app
     * @param password user´s chosen password (unencrypted)
     */
    void registerUser(UserDTO u, String password);

    /**
     * This method authenticates a user.
     * @param u DTO of user to be authenticated
     * @return true on success
     */
    boolean authenticate(NotAuthenticatedUserDTO u);

    /**
     * Updates user with attribute check.
     * @param u DTO of user to be updated
     */
    void updateUser(UserDTO u);

    /**
     * Deletes user.
     * @param u DTO of user to be deleted
     */
    void deleteUser(UserDTO u);

    /**
     * Returns User with given id
     * @param userId given id
     * @return DTO of found user on success, null otherwise
     */
    UserDTO findUserById(Long userId);

    /**
     * Returns User with given email
     * @param email given email
     * @return DTO of found user on success, null otherwise
     */
    UserDTO findUserByEmail(String email);

    /**
     * This method returns all users of Application
     * @return Collection of DTOs of all users of a app
     */
    Collection<UserDTO> getAllUsers();

}
