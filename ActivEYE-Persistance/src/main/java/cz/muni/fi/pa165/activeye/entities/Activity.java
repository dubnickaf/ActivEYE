package cz.muni.fi.pa165.activeye.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   25-10-2016
 */

@Entity
public class Activity {

    @Id // Specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_ID")
    private Long id;

    @NotNull // A variable cannot hold null value.
    @Column(nullable = false) // Whether the database column is nullable.
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Activity))
            return false;
        Activity other = (Activity) o;
        if (name == null) {
            if (other != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Activity{" + "id = " + id + ", name = " + name + ", caloriesRatio = " + caloriesRatio + '}';
    }

}
