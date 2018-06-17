package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
import com.fitness.capitol.gym.service.Workout_ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/exercise")
public class Exercise_WorkoutController {

    @Autowired
    private Workout_ExerciseService workout_exerciseService;

    @RequestMapping(value = "/{workout}/exercises",method = RequestMethod.GET)
    public List<Exercise> getAllExercisesByWorkout(@PathVariable("workout")Workout workout){
        return workout_exerciseService.findAllByWorkout(workout);
    }

    @RequestMapping(value = "/{workout}/addExercise",method = RequestMethod.POST)
    public void saveExercise(@PathVariable("workout")Workout workout, @RequestParam("name") String name){
        Exercise exercise = new Exercise(name);
        workout_exerciseService.saveExerciseForWorkout(new Workout_Exercise(workout,exercise));
    }

}
