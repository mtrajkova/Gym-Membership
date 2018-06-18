package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.excpetions.ExerciseAlreadyExistsException;
import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
import com.fitness.capitol.gym.service.ExerciseService;
import com.fitness.capitol.gym.service.WorkoutService;
import com.fitness.capitol.gym.service.Workout_ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/exercises")
public class Exercise_WorkoutController {

    @Autowired
    private Workout_ExerciseService workout_exerciseService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Exercise> getAllExercisesByWorkout(@RequestParam("workoutId") Long workoutId) {
        return workout_exerciseService.findAllByWorkoutId(workoutId);
    }

    @RequestMapping(value = "/addExercise", method = RequestMethod.POST)
    public void addExercise(@RequestParam("workoutId") Long workoutId, @RequestParam("name") String name) {

        Exercise exercise;
        exercise = exerciseService.findByName(name);
        if (exercise == null) {
            exercise = new Exercise();
            exercise.setName(name);
            exerciseService.save(exercise);
        } /*else {
            exercise = exerciseService.findByName(name);
        }*/

        Workout workout = workoutService.findById(workoutId);
        workout_exerciseService.saveExerciseForWorkout(new Workout_Exercise(workout, exercise));


    }

}
