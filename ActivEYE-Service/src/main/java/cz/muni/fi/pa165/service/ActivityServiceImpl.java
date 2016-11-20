package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.dao.ActivityDao;
import cz.muni.fi.pa165.activeye.entities.Activity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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
    public void create(Activity activity) {
        activityDao.create(activity);
    }

}
