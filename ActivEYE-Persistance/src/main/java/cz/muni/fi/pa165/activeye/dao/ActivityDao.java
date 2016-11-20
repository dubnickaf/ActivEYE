package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.Activity;

import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   25-10-2016
 */

public interface ActivityDao {

    /**
     * Creates a new Activity
     * @param a Activity to be created
     * @return void
     */
    public void create(Activity a);

    /**
     * Updates an Activity
     * @param a Activity to be updated
     * @return void
     */
    public void update(Activity a);

    /**
     * Deletes an Activity
     * @param a Activity to be deleted
     * @return void
     */
    public void delete(Activity a);

    /**
     * Returns Activity with given id
     * @param id id of required Activity
     * @return Activity with given id
     */
    public Activity findById(Long id);

    /**
     * Returns Activity with given name
     * @param name name of required Activity
     * @return Activity with given name
     */
    public Activity findByName(String name);

    /**
     * Returns List of all Activities
     * @return List of all Activities
     */
    public List<Activity> findAll();

}
