package cz.muni.fi.pa165.activeye.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author spriadka [422600]
 */
@Entity
@Table(name = "Records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    @JoinColumn(name = "ACTIVITY_ID")
    @OneToOne(cascade=CascadeType.PERSIST)
    private Activity activity;

    private BigDecimal burnedCalories;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startDate;

    @Temporal(TemporalType.TIMESTAMP)
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

    public Long getId(){
        return id;
    }


    @Override
    public String toString(){
        return "Record: [id:" + id + ", " +
                "user: " + user + ", " +
                "activity: " + activity + ", " +
                "burnedCalories: " + burnedCalories + ", " +
                "startDate: " + startDate.getTime() +  ", " +
                "endDate:" + endDate.getTime() + "]";
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null){
            return true;
        }
        if (!(o instanceof Record)){
            return false;
        }
        final Record other = (Record)o;
        if (!(user.equals(((Record) o).getUser()))){
            return false;
        }
        if (!(activity.equals(other.getActivity()))){
            return false;
        }
        if (!(startDate.equals(other.getStartDate()))){
            return false;
        }
        if(!(endDate.equals(other.getEndDate()))){
            return false;
        }
        return burnedCalories.equals(other.getBurnedCalories());
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + activity.hashCode();
        result = 31 * result + (burnedCalories != null ? burnedCalories.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
}
