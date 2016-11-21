package cz.muni.fi.pa165.activeye.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   21-11-2016
 */

public class ActivityDTO {

    private Long id;

    @NotNull
    private String name;

    private BigDecimal caloriesRatio;



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

    public BigDecimal getCaloriesRatio() {
        return caloriesRatio;
    }

    public void setCaloriesRatio(BigDecimal caloriesRatio) {
        this.caloriesRatio = caloriesRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDTO that = (ActivityDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(caloriesRatio, that.caloriesRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, caloriesRatio);
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caloriesRatio=" + caloriesRatio +
                '}';
    }

}
