package cz.muni.fi.pa165.activeye.dto;

import java.util.Set;

/**
 * @author Branislav Bajužík; 445772
 */
public class GroupDTO {

    private Long id;
    private Long creatorsUserId;
    private Set<UserDTO> users;
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

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
