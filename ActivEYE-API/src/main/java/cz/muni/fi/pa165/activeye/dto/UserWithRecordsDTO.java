package cz.muni.fi.pa165.activeye.dto;

import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.enums.UserRole;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 15.12.2016.
 */
public class UserWithRecordsDTO {
    private Long id;

    private String emailAddress;

    private Set<RecordDTO> activityRecords = new HashSet<RecordDTO>();

    private String name;

    private LocalDate bornDate;

    private Gender gender;

    private UserRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<RecordDTO> getActivityRecords() {
        return activityRecords;
    }

    public void setActivityRecords(Set<RecordDTO> activityRecords) {
        this.activityRecords = activityRecords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserWithRecordsDTO)) return false;

        UserWithRecordsDTO userWRDTO = (UserWithRecordsDTO) o;

        return getEmailAddress().equals(userWRDTO.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return getEmailAddress().hashCode();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + getId() +
                ", emailAddress='" + getEmailAddress() + '\'' +
                ", numberOfRecords=" + getActivityRecords().size() +
                ", name='" + getName() + '\'' +
                ", bornDate=" + getBornDate() +
                ", gender=" + getGender() +
                '}';
    }
}
