package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.User_WorkoutSubscriptionService;
import com.fitness.capitol.gym.service.WorkoutSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/myWorkoutSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class User_WorkoutSubscriptionController {
    @Autowired
    private User_WorkoutSubscriptionService user_workoutSubscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutSubscriptionService workoutSubscriptionService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    private List<WorkoutSubscription> getMyWorkoutSubs(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return user_workoutSubscriptionService.getAllWorkoutSubsByUser(user);
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    private void addWorkoutSubscription(@RequestParam("days") int days,
                                        @RequestParam("name") String name,
                                        @RequestParam("price") Long price,
                                        @RequestParam("username") String username) {
        WorkoutSubscription workoutSubscription = new WorkoutSubscription();
        workoutSubscription.setNumberOfDays(days);
        workoutSubscription.setSuper(name, price);
        workoutSubscriptionService.save(workoutSubscription);
        User user = userService.findByUsername(username);
        user_workoutSubscriptionService.save(workoutSubscription, user);
    }
}
