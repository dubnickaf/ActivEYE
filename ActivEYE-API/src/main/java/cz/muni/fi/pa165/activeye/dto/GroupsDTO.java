package cz.muni.fi.pa165.activeye.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Branislav Bajužík; 445772 on 16.12.16.
 */
public class GroupsDTO {
    private List<GroupDTO> list = new ArrayList<>();

    public List<GroupDTO> getList() {
        return list;
    }

    public void setList(List<GroupDTO> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsDTO)) return false;

        GroupsDTO groupsDTO = (GroupsDTO) o;

        return getList().equals(groupsDTO.getList());

    }

    @Override
    public int hashCode() {
        return getList().hashCode();
    }
}
