package cz.muni.fi.pa165.activeye.dto;

import java.math.BigDecimal;

/**
 * DTO representing user with its stats.
 * @author Filip Dubnička [445647]
 */
public class StatisticsOfUserDTO {
    private UserDTO userDTO;

    private BigDecimal totalCaloriesBurned;//null if totalRecords = 0

    private BigDecimal caloriesBurnedToday;//null if totalRecords = 0

    private BigDecimal averageBurnedCaloriesPerRecord;//null if totalRecords = 0

    private Integer totalRecords;

    private Integer recordsToday;

    private ActivityDTO mostUsedActivity;//null if totalRecords = 0

    private Integer numberOfGroups;

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setRecordsToday(Integer recordsToday) {
        this.recordsToday = recordsToday;
    }

    public Integer getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(Integer numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatisticsOfUserDTO that = (StatisticsOfUserDTO) o;

        return userDTO.equals(that.userDTO);

    }

    @Override
    public int hashCode() {
        return userDTO.hashCode();
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public BigDecimal getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(BigDecimal totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public BigDecimal getCaloriesBurnedToday() {
        return caloriesBurnedToday;
    }

    public void setCaloriesBurnedToday(BigDecimal caloriesBurnedToday) {
        this.caloriesBurnedToday = caloriesBurnedToday;
    }

    public BigDecimal getAverageBurnedCaloriesPerRecord() {
        return averageBurnedCaloriesPerRecord;
    }

    public void setAverageBurnedCaloriesPerRecord(BigDecimal averageBurnedCaloriesPerRecord) {
        this.averageBurnedCaloriesPerRecord = averageBurnedCaloriesPerRecord;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getRecordsToday() {
        return recordsToday;
    }

    public void setRecordsToday(int recordsToday) {
        this.recordsToday = recordsToday;
    }


    public ActivityDTO getMostUsedActivity() {
        return mostUsedActivity;
    }

    public void setMostUsedActivity(ActivityDTO mostUsedActivity) {
        this.mostUsedActivity = mostUsedActivity;
    }

    @Override
    public String toString() {
        return "StatisticsOfUserDTO{" +
                "userDTO=" + getUserDTO() +
                ", totalCaloriesBurned=" + getTotalCaloriesBurned() +
                ", caloriesBurnedToday=" + getCaloriesBurnedToday() +
                ", averageBurnedCaloriesPerRecord=" + getAverageBurnedCaloriesPerRecord() +
                ", totalRecords=" + getTotalRecords() +
                ", recordsToday=" + getRecordsToday() +
                ", mostUsedActivity=" + getMostUsedActivity() +
                '}';
    }
}
