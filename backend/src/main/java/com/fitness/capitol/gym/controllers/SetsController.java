package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.*;
import com.fitness.capitol.gym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/sets")
public class SetsController {
    @Autowired
    private Workout_ExerciseService workout_exerciseService;

    @Autowired
    private SetsService setsService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping(value = "/{username}/{workoutDate}/{exercise}", method = RequestMethod.GET)
    public List<Sets> getAllSets(@PathVariable("workoutDate") String workoutDate,
                                 @PathVariable("username") String username,
                                 @PathVariable("exercise") String exerciseName) {
        String[] parts = workoutDate.split("\\.");
       /* Date date = new Date();
        long time = Long.parseLong(parts[0]) + Long.parseLong(parts[1]) + Long.parseLong(parts[2]);
        date.setTime(time);*/
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(parts[2]),Integer.parseInt(parts[1]),Integer.parseInt(parts[0]),0,0);
        Client client = userService.findByUsername(username);
        Workout workout = workoutService.findByDate(date, client);
        Exercise exercise = new Exercise();
        List<Exercise> exercises = new ArrayList<>();
        List<Workout_Exercise> workout_exercises = workout_exerciseService.findAllByWorkout(workout);
        for(Workout_Exercise we : workout_exercises){
            exercises.add(we.getExercise());
        }
        for (Exercise ex : exercises) {
            if (ex.getName().equals(exerciseName)) {
                exercise = ex;
            }
        }
        return setsService.findAllByExercise(exercise);
    }

    @RequestMapping(value = "/addSet", method = RequestMethod.POST)
    public void addSet(@RequestParam("workoutDate") String workoutDate,
                       @RequestParam("username") String username,
                       @RequestParam("exercise") String exerciseName,
                       @RequestParam("weight") Long weight,
                       @RequestParam("numberOfSet") int numberOfSet,
                       @RequestParam("numberOfReps") int numberOfReps) {
        String[] parts = workoutDate.split("\\.");
        /*Date date = new Date();
        long time = Long.parseLong(parts[0]) + Long.parseLong(parts[1]) + Long.parseLong(parts[2]);
        date.setTime(time);*/
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(parts[2]),Integer.parseInt(parts[1]),Integer.parseInt(parts[0]),0,0);
        Client client = userService.findByUsername(username);
        Workout workout = workoutService.findByDate(date, client);
        Exercise exercise = new Exercise();
        List<Exercise> exercises = new ArrayList<>();
        List<Workout_Exercise> workout_exercises = workout_exerciseService.findAllByWorkout(workout);
        for(Workout_Exercise we : workout_exercises){
            exercises.add(we.getExercise());
        }
        for (Exercise ex : exercises) {
            if (ex.getName().equals(exerciseName)) {
                exercise = ex;
            }
        }
        Sets set = new Sets();
        set.setExercise(exercise);
        set.setNumberOfReps(numberOfReps);
        set.setNumberOfSet(numberOfSet);
        set.setWeight(weight);
        setsService.save(set);
    }
}
