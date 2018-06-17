package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<Exercise> getAllExercisesByWorkout(HttpSession session){
        return workout_exerciseService.findAllByWorkout((Workout)session.getAttribute("workout"));
    }

    @RequestMapping(value = "/addExercise",method = RequestMethod.POST)
    public void saveExercise(HttpSession session, @RequestParam("name") String name){
        Exercise exercise = new Exercise(name);

        workout_exerciseService.saveExerciseForWorkout(new Workout_Exercise((Workout)session.getAttribute("workout"),exercise));
    }

}
