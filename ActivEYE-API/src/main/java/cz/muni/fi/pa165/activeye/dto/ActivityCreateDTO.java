package cz.muni.fi.pa165.activeye.dto;

import javax.validation.constraints.NotNull;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   20-11-2016
 */

public class ActivityCreateDTO {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCreateDTO that = (ActivityCreateDTO) o;
        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
