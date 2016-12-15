package cz.muni.fi.pa165.activeye.entities;

import cz.muni.fi.pa165.activeye.annotations.Past;
import cz.muni.fi.pa165.activeye.enums.Gender;
import cz.muni.fi.pa165.activeye.enums.UserRole;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author Filip Dubnička [445647]
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    @NotNull
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.email}")
    @Column(unique=true)
    private String emailAddress;

    @Past
    private LocalDate bornDate;

    private Gender gender;

    @NotNull
    private UserRole role;

    @NotNull
    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Group> groups = new HashSet<Group>();

    @OneToMany(mappedBy = "user")
    private Set<Record> activityRecords = new HashSet<Record>();

    public User() {
    }

    public User(String name, String emailAddress, LocalDate bornDate, Gender gender, UserRole role) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.bornDate = bornDate;
        this.gender = gender;
        this.role = role;
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (! (o instanceof User)) return false;
        User user = (User) o;
        return getEmailAddress().equals(user.getEmailAddress());

    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }

    public Set<Record> getActivityRecords() {
        return activityRecords;
    }

    public void setActivityRecords(Set<Record> activityRecords) {
        this.activityRecords = activityRecords;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", emailAddress='" + getEmailAddress() + '\'' +
                ", bornDate=" + getBornDate() +
                ", gender=" + getGender() +
                '}';
    }
}
