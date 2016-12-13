package cz.muni.fi.pa165.samples.facade;

import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.service.ActivityService;
import cz.muni.fi.pa165.activeye.service.GroupService;
import cz.muni.fi.pa165.activeye.service.RecordService;
import cz.muni.fi.pa165.activeye.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by spriadka on 12/13/16.
 */
@Service
@Transactional
public class SampleDataLoadFacadeImpl implements SampleDataLoadFacade {

    @Inject
    private UserService userService;

    @Inject
    private ActivityService activityService;

    @Inject
    private RecordService recordService;

    @Inject
    private GroupService groupService;

    private List<Activity> activities;
    private Set<User> users = new HashSet<User>();
    private Set<Record> records = new HashSet<Record>();

    @Override
    public void loadData() {
        //Create Activities
        Activity[] a = {new Activity("Running", BigDecimal.valueOf(654)),
                new Activity("Swimming", BigDecimal.valueOf(572)),
                new Activity("Football", BigDecimal.valueOf(654)),
                new Activity("Cycling", BigDecimal.valueOf(490))};
        for (Activity ac : a) {
            activityService.create(ac);
        }
        activities = activityService.findAll();

        //Create Users
        for (int i = 0; i<10; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(2000+i,1,1);
            Gender gender = i%2 == 0 ? Gender.MALE : Gender.FEMALE;
            User u = new User("user"+i, "user"+i+"@mail.com", cal.getTime(), gender);
            userService.registerUser(u, "user"+i);
        }
        users.addAll(userService.getAllUsers());

        List<User> userList = new ArrayList<User>(users);
        for (int i = 0; i<10; i++) {
            Calendar start = Calendar.getInstance();
            start.set(2016,1,1,12,10+i);
            Calendar end = Calendar.getInstance();
            end.set(2016,1,1,12,20+i);
            Record r = new Record(userList.get(i%10), activities.get(i%4),start, end);
            recordService.createRecord(r);
        }
        records.addAll(recordService.getAllRecords());


    }
}
