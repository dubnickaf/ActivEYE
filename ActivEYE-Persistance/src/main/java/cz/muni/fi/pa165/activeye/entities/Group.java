package cz.muni.fi.pa165.activeye.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Branislav Bajuzik; 442772
 */
@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long creatorsUserId;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<User>();

    @NotNull
    @Column(nullable = false)
    private String name;

    public Group() {
    }

    public Group(Long creatorsUserId, Set<User> users, String name) {
        this.creatorsUserId = creatorsUserId;
        this.name = name;

        for (User u : users) {
            this.addUser(u);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorsUserId() {
        return creatorsUserId;
    }

    public void setCreatorsUserId(Long creatorsUserId) {
        this.creatorsUserId = creatorsUserId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupSize() {
        return users.size();
    }

    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
        Set<User> users = getUsers();
        if (users.add(user)) {
            setUsers(users);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Group:{id:").append(getId()).append(", name:").append(getName()).append(", creatorsUserId:")
                .append(getCreatorsUserId()).append(", users:").append(getUsers().toString()).append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }

        final Group group = (Group) o;
        
        return getId().equals(group.getId()) &&
               getCreatorsUserId().equals(group.getCreatorsUserId()) &&
               getUsers().equals(group.getUsers()) &&
               getName().equals(group.getName());

    }

    @Override
    public int hashCode() {
        int result = 11;
        result *= 31 + (getId() != null ? getId().hashCode() : 0);
        result *= 31 + (getCreatorsUserId() != null ? getCreatorsUserId().hashCode() : 0);
        result *= 31 + (getUsers() != null ? getUsers().hashCode() : 0);
        return result * 31 + (getName() != null ? getName().hashCode() : 0);
    }
}
