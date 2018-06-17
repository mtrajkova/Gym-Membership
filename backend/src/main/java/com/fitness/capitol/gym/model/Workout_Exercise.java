package com.fitness.capitol.gym.model;

import javax.persistence.*;

@Entity
@Table(name = "Workout_Exercise")
public class Workout_Exercise {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private Workout workout;

    @JoinColumn
    @ManyToOne
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
}
