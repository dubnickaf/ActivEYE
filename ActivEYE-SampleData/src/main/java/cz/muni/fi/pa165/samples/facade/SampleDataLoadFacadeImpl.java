package cz.muni.fi.pa165.samples.facade;

import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.Group;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.enums.UserRole;
import cz.muni.fi.pa165.activeye.service.ActivityService;
import cz.muni.fi.pa165.activeye.service.GroupService;
import cz.muni.fi.pa165.activeye.service.RecordService;
import cz.muni.fi.pa165.activeye.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private List<User> users = new ArrayList<User>();
    private List<Record> records = new ArrayList<Record>();
    private List<Group> groups = new ArrayList<Group>();

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
            User u = new User("user"+i, "user"+i+"@mail.com", LocalDate.from(Instant.ofEpochMilli(cal.getTimeInMillis()).atZone(ZoneId.systemDefault()).toLocalDate()), gender, UserRole.USER);
            userService.registerUser(u, "user"+i);
        }
        users.addAll(userService.getAllUsers());

        //Create Records
        for (int i = 0; i<10; i++) {
            LocalDateTime start = LocalDateTime.now().withYear(2016).withMonth(1).withDayOfMonth(1).withHour(12).withMinute(10+i);
            LocalDateTime end = LocalDateTime.now().withYear(2016).withMonth(1).withDayOfMonth(1).withHour(12).withMinute(20+i);
            Record r = new Record(users.get(i%9), activities.get(i%4),start, end);
            recordService.createRecord(r);
        }
        records.addAll(recordService.getAllRecords());
        for (int i = 0; i<10; i++) {
            users.get(i%9).getActivityRecords().add(records.get(i));
            userService.updateUser(users.get(i%9));
        }

        //Create Groups
        Set<User> temp = new HashSet<User>(Arrays.asList(users.get(0), users.get(4), users.get(8)));
        groups.add(new Group(users.get(0).getId(), temp, "Runners"));

        temp = new HashSet<User>(Arrays.asList(users.get(1), users.get(5), users.get(0)));
        groups.add(new Group(users.get(1).getId(), temp, "Swimmers"));

        temp = new HashSet<User>(Arrays.asList(users.get(2), users.get(6)));
        groups.add(new Group(users.get(2).getId(), temp, "Footballers"));

        temp = new HashSet<User>(Arrays.asList(users.get(3), users.get(7)));
        groups.add(new Group(users.get(3).getId(), temp, "Cyclers"));

        temp = new HashSet<User>(Arrays.asList(users.get(1), users.get(3), users.get(5), users.get(7)));
        groups.add(new Group(users.get(1).getId(), temp, "Friends"));

        for (Group g : groups) {
            groupService.create(g);
            for (User u : g.getUsers()) {
                u.getGroups().add(g);
                userService.updateUser(u);
            }
        }
        //Next 2 lines needed to update fields, but not needed now, because everything is created.
        //Uncomment them if you add code below them.
        //groups = new ArrayList<Group>(groupService.findAll());
        //users = new ArrayList<User>(userService.getAllUsers());

    }
}
