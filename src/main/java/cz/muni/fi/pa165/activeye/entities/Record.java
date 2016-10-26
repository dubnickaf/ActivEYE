package cz.muni.fi.pa165.activeye.entities;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "ACTIVITY_ID")
    private Activity activity;

    private BigDecimal burnedCalories;

    //Duration of in miliseconds
    private Long time;


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

    public void setTime(long time){
        this.time = time;
    }

    public Long getId(){
        return id;
    }

    public Long getTime(){
        return time;
    }

    @Override
    public String toString(){
        return "Record: [id:" + id + ", " +
                "user: " + user + ", " +
                "activity: " + activity + ", " +
                "burnedCalories: " + burnedCalories + ", " +
                "time: " + time + "]";
    }
}
