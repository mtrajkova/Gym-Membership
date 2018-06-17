package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Workout_Exercise")
public class Workout_Exercise implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Workout workout;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Exercise exercise;

    public Workout_Exercise(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
    }

    public Workout_Exercise(){}

    public Workout getWorkout() {
        return workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout_Exercise)) return false;
        Workout_Exercise that = (Workout_Exercise) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(getWorkout(), that.getWorkout()) &&
                Objects.equals(getExercise(), that.getExercise());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, getWorkout(), getExercise());
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
