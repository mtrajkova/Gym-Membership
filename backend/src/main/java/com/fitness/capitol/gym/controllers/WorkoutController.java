package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.excpetions.UserEmptyException;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workouts", produces = MediaType.APPLICATION_JSON_VALUE)
@SessionAttributes("user")

public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public ResponseEntity findAllWorkoutsByUser(@PathVariable("name") String name) throws UserEmptyException {
        try {
            User user = userService.findByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(workoutService.findAllByUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }

    }

    @RequestMapping(value = "/addWorkout", method = RequestMethod.POST)
    public ResponseEntity addWorkout(@RequestParam("name") String name) throws UserEmptyException {

        try {
            Workout workout = new Workout();
            User user = userService.findByName(name);
            workout.setDate(LocalDateTime.now());
            workout.setUser(user);
            workoutService.save(workout);
            return ResponseEntity.status(HttpStatus.OK).body(workout);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }
    }
}
