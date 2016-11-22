package cz.muni.fi.pa165.activeye.dto;

import cz.muni.fi.pa165.activeye.entities.User;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Branislav Bajužík; 445772
 */
public class GroupDTO {

    private Long id;
    private Long creatorsUserId;
    private Set<User> users;
    private String name;

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
}
