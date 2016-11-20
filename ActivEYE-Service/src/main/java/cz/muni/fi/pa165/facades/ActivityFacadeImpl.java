package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.activeye.dto.ActivityCreateDTO;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.facades.ActivityFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   20-11-2016
 */

public class ActivityFacadeImpl implements ActivityFacade {

    @Inject
    private ActivityService activityService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createActivity(ActivityCreateDTO activityCreateDTO) {
        if (activityCreateDTO == null) {
            throw new IllegalArgumentException("Activity report can't be null");
        }
        Activity mappedActivity = beanMappingService.mapTo(activityCreateDTO, Activity.class);

        activityService.create(mappedActivity);
        return mappedActivity.getId();
    }
}
