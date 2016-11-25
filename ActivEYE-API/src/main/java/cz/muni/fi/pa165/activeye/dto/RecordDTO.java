package cz.muni.fi.pa165.activeye.dto;



import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by spriadka on 11/22/16.
 * @author spriadka
 */
public class RecordDTO {
    private Long id;
    private UserDTO user;
    private ActivityDTO activity;
    private BigDecimal burnedCalories;
    private Calendar startDate;
    private Calendar endDate;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ActivityDTO getActivity() {
        return activity;
    }

    public void setActivity(ActivityDTO activity) {
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
