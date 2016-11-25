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
        activityDao.create(a);
    }

    @Override
    public void update(Activity a) {
        activityDao.update(a);
    }

    @Override
    public void delete(Activity a) {
        activityDao.delete(a);
    }

    @Override
    public Activity findById(Long id) {
        return activityDao.findById(id);
    }

    @Override
    public Activity findByName(String name) {
        return activityDao.findByName(name);
    }

    @Override
    public List<Activity> findAll() {
        return activityDao.findAll();
    }

}
