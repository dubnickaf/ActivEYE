package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.ActivityDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import cz.muni.fi.pa165.activeye.exceptions.NoSuchEntityFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
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
        if (a.getName() == null) {
            throw new IllegalArgumentException("Activity's name cannot be null.");
        }

        try {
            activityDao.create(a);
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
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

        try {
            activityDao.update(a);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public void delete(Activity a) {
        if (a == null) {
            throw new IllegalArgumentException("Activity cannot be null.");
        }
        if (a.getId() == null) {
            throw new IllegalArgumentException("Activity's id cannot be null.");
        }


        try {
            activityDao.delete(a);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public Activity findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find activity with id null.");
        }

        Activity activity;

        try {
            activity = activityDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }

        if (activity == null){
            throw new NoSuchEntityFoundException("Cannot find activity with id " + id);
        }

        return activity;
    }

    @Override
    public Activity findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot find activity with name null.");
        }

        Activity activity;

        try {
            activity = activityDao.findByName(name);
        } catch (IllegalArgumentException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }

        if (activity == null){
            throw new NoSuchEntityFoundException("Cannot find activity with name " + name);
        }

        return activity;
    }

    @Override
    public List<Activity> findAll() {
        return activityDao.findAll();
    }

}
