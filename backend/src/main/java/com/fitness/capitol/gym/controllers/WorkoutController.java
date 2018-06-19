package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.WorkoutService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@RestController
@RequestMapping(value = "/workouts", produces = MediaType.APPLICATION_JSON_VALUE)
@SessionAttributes("user")

public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity findAllWorkoutsByUser(@PathVariable("username") String username) {
        try {
            User user = userService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.findAllByUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }

    }

    @RequestMapping(value = "/addWorkout", method = RequestMethod.POST)
    public ResponseEntity addWorkout(@RequestParam("username") String username,
                                     @RequestParam("workoutname") String workoutName) {
        User user;
        try {
            user = this.userService.findByUsername(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        Date date = new Date();
        Workout workout = new Workout();
        workout.setUser(user);
        workout.setWorkoutName(workoutName);
        workout.setDate(date);
        try {
            workoutService.save(workout);
            return ResponseEntity.status(HttpStatus.OK).body(workout);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
