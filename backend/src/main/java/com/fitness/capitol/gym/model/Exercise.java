package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Exercise")
public class Exercise implements Serializable {
    @Id
    @Column(unique = true)
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
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(getName(), exercise.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName());
    }
}
