package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.StatisticsOfUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.dto.UserWithRecordsDTO;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;

import java.util.Collection;

/**
 * User´s facades
 * @author Filip Dubnička [445647]
 */
public interface UserFacade {

    /**
     * This method registers the given user with given password
     * @param u DTO of user to be registered to app
     * @param password user´s chosen password (unencrypted)
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user does not exist
     */
    void registerUser(UserDTO u, String password);

    /**
     * This method authenticates a user.
     * @param u DTO of user to be authenticated
     * @return true on success
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user does not exist
     */
    boolean authenticate(NotAuthenticatedUserDTO u);

    /**
     * Updates user with attribute check.
     * @param u DTO of user to be updated
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user does not exist
     */
    void updateUser(UserDTO u);

    /**
     * Deletes user.
     * @param u DTO of user to be deleted
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user does not exist
     */
    void deleteUser(UserDTO u);

    /**
     * Returns User with given id
     * @param userId given id
     * @return DTO of found user on success, null otherwise
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user with given email does not exist
     */
    UserDTO findUserById(Long userId);

    /**
     * Returns User with given email
     * @param email given email
     * @return DTO of found user on success, null otherwise
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user with given email does not exist
     */
    UserDTO findUserByEmail(String email);

    /**
     * This method returns all users of Application
     * @return Collection of DTOs of all users of a app
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if no user is already in system
     */
    Collection<UserDTO> getAllUsers();

    /**
     * Returns stats for given user.
     *
     * @param u users DTO
     * @return statistics DTO, be careful cos some of the attributes can be null if user does not have any record.
     * @throws IllegalArgumentException   if userDTO is null
     * @throws NoSuchEntityFoundException if user does not exist
     */
    StatisticsOfUserDTO getStatistics(UserDTO u);

    /**
     * Returns User with given id \w records
     * @param userId given id
     * @return DTO of found user on success, null otherwise
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user with given email does not exist
     */
    UserWithRecordsDTO findUserWithRecordsById(Long userId);
    /**
     * Returns User with given email \w records
     * @param email given email
     * @return DTO of found user on success, null otherwise
     * @throws IllegalArgumentException if wrong argument is given
     * @throws NoSuchEntityFoundException if user with given email does not exist
     */
    UserWithRecordsDTO findUserWithRecordsByEmail(String email);
}
