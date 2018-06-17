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
    private String name;

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
