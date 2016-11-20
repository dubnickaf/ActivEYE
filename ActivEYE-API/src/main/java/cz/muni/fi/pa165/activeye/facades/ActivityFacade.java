package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.ActivityCreateDTO;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   20-11-2016
 */

public interface ActivityFacade {

    Long createActivity(ActivityCreateDTO activityCreateDTO);

}
