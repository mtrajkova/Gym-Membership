package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;

import java.util.List;

public interface Workout_ExerciseService {
    List<Exercise> findAllByWorkout(Workout workout);
    void saveExerciseForWorkout(Workout_Exercise workout_exercise);
}

