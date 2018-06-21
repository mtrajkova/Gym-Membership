package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
import com.fitness.capitol.gym.persistance.Exercise_WorkoutRepository;
import com.fitness.capitol.gym.service.Workout_ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Workout_ExerciseServiceImpl implements Workout_ExerciseService {
    @Autowired
    private Exercise_WorkoutRepository exercise_workoutRepository;

    @Override
    public List<Workout_Exercise> findAllByWorkout(Workout workout) {
        return exercise_workoutRepository.findAllByWorkout(workout);
    }

    @Override
    public void saveExerciseForWorkout(Workout_Exercise workout_exercise) {
        exercise_workoutRepository.save(workout_exercise);
    }

    @Override
    public void deleteByExerciseAndWorkout(Exercise exercise, Workout workout) {
        exercise_workoutRepository.deleteByExerciseAndWorkout(exercise,workout);
    }
}
