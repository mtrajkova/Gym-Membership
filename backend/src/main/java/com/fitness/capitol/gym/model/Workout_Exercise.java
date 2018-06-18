package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Workout_Exercise")
public class Workout_Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutExerciseId;

    @JoinColumn
   // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Workout workout;

    @JoinColumn
   // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Exercise exercise;

    public Workout_Exercise(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
    }

    public Workout_Exercise() {
    }

    public Long getWorkoutExerciseId() {
        return workoutExerciseId;
    }

    public void setWorkoutExerciseId(Long workoutExerciseId) {
        this.workoutExerciseId = workoutExerciseId;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
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
        if (!(o instanceof Workout_Exercise)) return false;
        Workout_Exercise that = (Workout_Exercise) o;
        return Objects.equals(getWorkoutExerciseId(), that.getWorkoutExerciseId()) &&
                Objects.equals(getWorkout(), that.getWorkout()) &&
                Objects.equals(getExercise(), that.getExercise());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorkoutExerciseId(), getWorkout(), getExercise());
    }
}
