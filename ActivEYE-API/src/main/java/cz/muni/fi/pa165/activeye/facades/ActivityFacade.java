package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.ActivityDTO;

import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   20-11-2016
 */

public interface ActivityFacade {

    /**
     * Creates a new Activity
     * @param activityDTO Activity to be created
     * @return void
     */
    void createActivity(ActivityDTO activityDTO);

    /**
     * Updates an Activity
     * @param activityDTO Activity to be updated
     * @return void
     */
    void updateActivity(ActivityDTO activityDTO);

    /**
     * Deletes an Activity
     * @param id of an Activity to be deleted
     * @return void
     */
    void deleteActivity(Long id);

    /**
     * Returns ActivityDTO with given id
     * @param id id of required Activity
     * @return ActivityDTO with given id
     */
    ActivityDTO findById(Long id);

    /**
     * Returns ActivityDTO with given name
     * @param name name of required Activity
     * @return ActivityDTO with given name
     */
    ActivityDTO findByName(String name);

    /**
     * Returns List of all Activities in DTO object
     * @return List of all Activities in DTO object
     */
    List<ActivityDTO> findAll();

}
