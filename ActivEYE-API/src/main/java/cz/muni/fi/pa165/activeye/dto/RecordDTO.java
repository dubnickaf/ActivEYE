package cz.muni.fi.pa165.activeye.dto;



import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordDTO recordDTO = (RecordDTO) o;

        if (getUser() != null ? !getUser().equals(recordDTO.getUser()) : recordDTO.getUser() != null) return false;
        if (getActivity() != null ? !getActivity().equals(recordDTO.getActivity()) : recordDTO.getActivity() != null)
            return false;
        if (getBurnedCalories() != null ? !getBurnedCalories().equals(recordDTO.getBurnedCalories()) : recordDTO.getBurnedCalories() != null)
            return false;
        if (getStartDate() != null ? !getStartDate().equals(recordDTO.getStartDate()) : recordDTO.getStartDate() != null)
            return false;
        return !(getEndDate() != null ? !getEndDate().equals(recordDTO.getEndDate()) : recordDTO.getEndDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getActivity() != null ? getActivity().hashCode() : 0);
        result = 31 * result + (getBurnedCalories() != null ? getBurnedCalories().hashCode() : 0);
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        return result;
    }
}
