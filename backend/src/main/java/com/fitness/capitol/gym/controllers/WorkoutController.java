package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.excpetions.UserEmptyException;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/workouts")
@SessionAttributes("user")

public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Workout> findAllWorkoutsByUser(String username) {
        User user = userService.findByName(username);
        return workoutService.findAllByUser(user);
    }

    @RequestMapping(value = "/addWorkout", method = RequestMethod.POST)
    public void addWorkout(String username) throws UserEmptyException {
        Workout workout = new Workout();
        User user = userService.findByName(username);
        if (user.getName().isEmpty())
            throw new UserEmptyException("User empty");
        else {
            workout.setUser(user);
            workoutService.save(workout);
        }

    }
}
