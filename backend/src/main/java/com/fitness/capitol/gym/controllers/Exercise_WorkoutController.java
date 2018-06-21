package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
import com.fitness.capitol.gym.service.ExerciseService;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.WorkoutService;
import com.fitness.capitol.gym.service.Workout_ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/exercises", produces = MediaType.APPLICATION_JSON_VALUE)
public class Exercise_WorkoutController {

    @Autowired
    private Workout_ExerciseService workout_exerciseService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;

    //    PROBAJ GOOOO OVA **************
    @RequestMapping(value = "/{workoutDate}/{username}/{workoutName}", method = RequestMethod.GET)
    public List<Exercise> getAllExercisesByWorkout(@PathVariable("workoutDate") String workoutDate, @PathVariable("username") String username, @PathVariable("workoutName") String workoutName) {
        Client client = userService.findByUsername(username);
        String[] parts = workoutDate.split("\\.");
        /*long time = Long.parseLong(parts[0]) + Long.parseLong(parts[1]) + Long.parseLong(parts[2]);
        Date date = new Date();
        date.setTime(time);*/
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]), 0, 0);
        Workout workout = new Workout();
        workout = workoutService.findByDateAndWorkoutNameAndClient(date, workoutName, client);
        List<Workout_Exercise> workout_exercises = workout_exerciseService.findAllByWorkout(workout);
        List<Exercise> exercises = new ArrayList<>();
        for (Workout_Exercise we : workout_exercises) {
            exercises.add(we.getExercise());
        }
        return exercises;
    }

    @RequestMapping(value = "/addExercise", method = RequestMethod.POST)
    public ResponseEntity addExercise(@RequestParam("workoutDate") String workoutDate,
                                      @RequestParam("name") String name,
                                      @RequestParam("username") String username,
                                      @RequestParam("workoutName") String workoutName) {
        Client client;
        Exercise exercise;
        try {
            client = userService.findByUsername(username);
            exercise = exerciseService.findByName(name);

            if (exercise == null) {
                exercise = new Exercise();
                exercise.setName(name);
                exerciseService.save(exercise);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        String[] parts = workoutDate.split("\\.");
        /*long time = Long.parseLong(parts[0]) + Long.parseLong(parts[1]) + Long.parseLong(parts[2]);
        Date date = new Date();
        date.setTime(time);*/
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]), 0, 0);
        Workout workout = workoutService.findByDateAndWorkoutNameAndClient(date, workoutName, client);
        if (workout == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(date);
        }
        Workout_Exercise workout_exercise = new Workout_Exercise();
        workout_exercise.setExercise(exercise);
        workout_exercise.setWorkout(workout);
        workout_exerciseService.saveExerciseForWorkout(workout_exercise);
        return ResponseEntity.status(HttpStatus.OK).body(workout_exercise);

    }

   /* @RequestMapping(value = "/delete/{exerciseName}/{workoutDate}/{username}", method = RequestMethod.DELETE)
    public void deleteExerciseForWorkout(@PathVariable("exerciseName") String name, @PathVariable("workoutDate") String workoutDate, @PathVariable("username") String username) {
        Exercise exercise = exerciseService.findByName(name);
        String[] parts = workoutDate.split("\\.");
        long time = Long.parseLong(parts[0]) + Long.parseLong(parts[1]) + Long.parseLong(parts[2]);
        Date date = new Date();
        date.setTime(time);
        Workout workout = workoutService.findByDate(date, userService.findByUsername(username));
        workout_exerciseService.deleteByExerciseAndWorkout(exercise, workout);
    }*/

}
