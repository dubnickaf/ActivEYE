package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.User;
import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author Filip Dubnička [445647]
 */
public interface UserDao {
    /**
     *
     * @param user
     */
    public void create(User user);

    /**
     *
     * @param user
     */
    public void update(User user);

    /**
     *
     * @param user
     */
    public void delete(User user);

    /**
     *
     * @param id
     * @return
     */
    public User findUserById(Long id);

    /**
     *
     * @param email-
     * @return
     */
    public User findUserByEmail(String email);

    /**
     *
     * @return
     */
    public List findAll();
}
