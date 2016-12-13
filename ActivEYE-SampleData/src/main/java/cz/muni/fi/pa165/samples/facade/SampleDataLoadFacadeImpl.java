package cz.muni.fi.pa165.samples.facade;

import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.service.ActivityService;
import cz.muni.fi.pa165.activeye.service.GroupService;
import cz.muni.fi.pa165.activeye.service.RecordService;
import cz.muni.fi.pa165.activeye.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    private GroupService groupService;

    private Map<String,User> users;

    @Override
    public void loadData() {
        createUsers();
    }

    private void createUsers(){
        users = new HashMap<String, User>();
        users.put("Johny Bravo",user("Johny Bravo","johny@bravo.com",Gender.MALE,"1-1-1990"));
        userService.registerUser(users.get("Johny Bravo"),"bravo");
    }

    private User user(String name, String email, Gender gender, String dateString){
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmailAddress(email);
        newUser.setGender(gender);
        Calendar date = Calendar.getInstance();
        String[] tokens = dateString.split("-");
        date.set(Calendar.DAY_OF_MONTH,Integer.valueOf(tokens[0]));
        date.set(Calendar.MONTH,Integer.valueOf(tokens[1]));
        date.set(Calendar.YEAR,Integer.valueOf(tokens[2]));
        newUser.setBornDate(date.getTime());
        return newUser;
    }
}
