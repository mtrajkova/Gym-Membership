package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;

import java.util.List;

public interface Workout_ExerciseService {
//    List<Workout_Exercise> findAllByWorkout(Workout workout);

    List<Workout_Exercise> findAllByWorkout(Workout workout);

    void saveExerciseForWorkout(Workout_Exercise workout_exercise);

    void deleteByExerciseAndWorkout(Exercise exercise, Workout workout);


}

