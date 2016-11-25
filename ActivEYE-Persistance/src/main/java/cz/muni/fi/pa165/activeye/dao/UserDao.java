package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.User;

import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author Filip Dubnička [445647]
 */
public interface UserDao {
    /**
     * Creates a new User in DB
     * @param user User to be created
     * @throws IllegalArgumentException if wrong argument is given
     */
    public void create(User user);

    /**
     * Updates a User in DB
     * @param user User to be updated
     * @throws IllegalArgumentException if wrong argument is given
     */
    public void update(User user);

    /**
     * Deletes a User in DB
     * @param user User to be deleted
     * @throws IllegalArgumentException if wrong argument is given
     */
    public void delete(User user);

    /**
     * Finds a User in DB by id
     * @param id id of User to be found
     * @return user with chosen id
     * @throws IllegalArgumentException if wrong argument is given
     */
    public User findUserById(Long id);

    /**
     * Finds a User in DB by email
     * @param email email of User to be found
     * @return user with chosen email
     * @throws IllegalArgumentException if wrong argument is given
     */
    public User findUserByEmail(String email);

    /**
     * Finds all User from DB
     * @return all Users from DB
     */
    public List<User> findAll();
}
