package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Workout> findAllWorkoutsByUser(HttpSession session) {
        return workoutService.findAllByUser((User)session.getAttribute("user"));
    }

    @RequestMapping(value = "/addWorkout",method = RequestMethod.POST)
    public void addWorkout( HttpSession session) {
        LocalDateTime date = LocalDateTime.now();
        workoutService.save(new Workout(date,(User)session.getAttribute("user")));

    }
}
