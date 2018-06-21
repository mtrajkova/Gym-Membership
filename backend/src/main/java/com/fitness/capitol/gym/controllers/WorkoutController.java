package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "/workouts", produces = MediaType.APPLICATION_JSON_VALUE)
@SessionAttributes("user")
@CrossOrigin
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity findAllWorkoutsByUser(@PathVariable("username") String username) {
        try {
            Client client = userService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.findAllByClient(client));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist");
        }

    }

    @RequestMapping(value = "/addWorkout", method = RequestMethod.POST)
    public ResponseEntity addWorkout(@RequestParam("username") String username,
                                     @RequestParam("workoutname") String workoutName) {
        Client client;
        try {
            client = this.userService.findByUsername(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        LocalDateTime dateTemp = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.of(dateTemp.getYear(), dateTemp.getMonth(), dateTemp.getDayOfMonth(),0,0);
        Workout workout = new Workout();
        workout.setClient(client);
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
