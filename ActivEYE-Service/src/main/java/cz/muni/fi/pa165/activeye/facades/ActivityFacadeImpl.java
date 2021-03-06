package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.ActivityDTO;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   20-11-2016
 */
@Service
@Transactional
public class ActivityFacadeImpl implements ActivityFacade {

    @Inject
    private ActivityService activityService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void createActivity(ActivityDTO activityDTO) {
        if (activityDTO == null) {
            throw new IllegalArgumentException("Activity can't be null.");
        }

        Activity mappedActivity = beanMappingService.mapTo(activityDTO, Activity.class);

        activityService.create(mappedActivity);
    }

    @Override
    public void updateActivity(ActivityDTO activityDTO) {
        if (activityDTO == null) {
            throw new IllegalArgumentException("Activity can't be null.");
        }

        Activity mappedActivity = beanMappingService.mapTo(activityDTO, Activity.class);

        if (activityService.findById(mappedActivity.getId()) == null) {
            throw new NoSuchEntityFoundException("Required Activity doesn't exist.");
        }

        activityService.update(mappedActivity);
    }

    @Override
    public void deleteActivity(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Activity's ID can't be null.");
        }

        Activity a = activityService.findById(id);

        if (a == null) {
            throw new NoSuchEntityFoundException("Required Activity doesn't exist.");
        }

        activityService.delete(a);
    }

    @Override
    public ActivityDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Activity's ID can't be null.");
        }

        Activity a = activityService.findById(id);

        if (a == null) {
            throw new NoSuchEntityFoundException("Required Activity doesn't exist.");
        }

        return beanMappingService.mapTo(a, ActivityDTO.class);
    }

    @Override
    public ActivityDTO findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Activity's name can't be null.");
        }

        Activity a = activityService.findByName(name);

        if (a == null) {
            throw new NoSuchEntityFoundException("Required Activity doesn't exist.");
        }

        return beanMappingService.mapTo(a, ActivityDTO.class);
    }

    @Override
    public List<ActivityDTO> findAll() {
        return beanMappingService.mapTo(activityService.findAll(), ActivityDTO.class);
    }

}
