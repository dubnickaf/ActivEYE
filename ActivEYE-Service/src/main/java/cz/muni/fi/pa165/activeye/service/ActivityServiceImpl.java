package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.ActivityDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   20-11-2016
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Inject
    private ActivityDao activityDao;

    @Override
    public void create(Activity a) {
        if (a == null) {
            throw new IllegalArgumentException("Activity cannot be null.");
        }
        if (a.getId() == null) {
            throw new IllegalArgumentException("Activity's id cannot be null.");
        }
        if (a.getName() == null) {
            throw new IllegalArgumentException("Activity's name cannot be null.");
        }

        activityDao.create(a);
    }

    @Override
    public void update(Activity a) {
        if (a == null) {
            throw new IllegalArgumentException("Activity cannot be null.");
        }
        if (a.getId() == null) {
            throw new IllegalArgumentException("Activity's id cannot be null.");
        }
        if (a.getName() == null) {
            throw new IllegalArgumentException("Activity's name cannot be null.");
        }
        activityDao.update(a);
    }

    @Override
    public void delete(Activity a) {
        if (a == null) {
            throw new IllegalArgumentException("Activity cannot be null.");
        }
        if (a.getId() == null) {
            throw new IllegalArgumentException("Activity's id cannot be null.");
        }

        activityDao.delete(a);
    }

    @Override
    public Activity findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find activity with id null.");
        }
        return activityDao.findById(id);
    }

    @Override
    public Activity findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot find activity with name null.");
        }

        return activityDao.findByName(name);
    }

    @Override
    public List<Activity> findAll() {
        return activityDao.findAll();
    }

}
