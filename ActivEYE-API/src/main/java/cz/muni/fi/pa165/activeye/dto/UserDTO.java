package cz.muni.fi.pa165.activeye.dto;

import java.util.Date;

import cz.muni.fi.pa165.activeye.enums.Gender;

/**
 * DTO representing user.
 * @author Filip Dubnička [445647]
 */
public class UserDTO {

    private Long id;

    private String name;

    private String emailAddress;

    private Date bornDate;

    private Gender gender;

    private String passwordHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        return getEmailAddress().equals(userDTO.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return getEmailAddress().hashCode();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", emailAddress='" + getEmailAddress() + '\'' +
                ", bornDate=" + getBornDate() +
                ", gender=" + getGender() +
                '}';
    }
}
