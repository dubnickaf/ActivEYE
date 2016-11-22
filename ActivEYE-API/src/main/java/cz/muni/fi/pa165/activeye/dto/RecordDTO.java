package cz.muni.fi.pa165.activeye.dto;

import cz.muni.fi.pa165.activeye.entities.Activity;
import cz.muni.fi.pa165.activeye.entities.User;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by spriadka on 11/22/16.
 * @author spriadka
 */
public class RecordDTO {
    private Long id;
    private User user;
    private Activity activity;
    private BigDecimal burnedCalories;
    private Calendar startDate;
    private Calendar endDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public BigDecimal getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(BigDecimal burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
}
