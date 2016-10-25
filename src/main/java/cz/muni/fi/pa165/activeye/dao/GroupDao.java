package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.User;
import java.util.List;

/**
 * @author Branislav Bajuzik 442772
 */
public interface GroupDao {
    /**
     *
     * @param group
     */
    public void create(Group group);

    /**
     *
     * @param group
     */
    public void update(Group group);

    /**
     *
     * @param group
     */
    public void delete(Group group);

    /**
     *
     * @param id
     * @return
     */
    public Group findUserById(Long id);

    /**
     *
     * @return
     */
    public List<User> findAllUsers();

    /**
     *
     * @param user
     * @return
     */
    public boolean isUserInGroup(User user);
}
