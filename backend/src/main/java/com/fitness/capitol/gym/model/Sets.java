package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "sets")
public class Sets implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfSet;
    private Long weight;
    private int numberOfReps;

    @JoinColumn
    @ManyToOne
    private Exercise exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfSet() {
        return numberOfSet;
    }

    public void setNumberOfSet(int numberOfSet) {
        this.numberOfSet = numberOfSet;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public int getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sets)) return false;
        Sets sets = (Sets) o;
        return getNumberOfSet() == sets.getNumberOfSet() &&
                getNumberOfReps() == sets.getNumberOfReps() &&
                Objects.equals(getId(), sets.getId()) &&
                Objects.equals(getWeight(), sets.getWeight()) &&
                Objects.equals(getExercise(), sets.getExercise());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getNumberOfSet(), getWeight(), getNumberOfReps(), getExercise());
    }
}
