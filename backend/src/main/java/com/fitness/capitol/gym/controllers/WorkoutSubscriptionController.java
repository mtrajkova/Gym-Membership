package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/workoutSubscriptions")
public class WorkoutSubscriptionController {
    @Autowired
    private WorkoutSubscriptionRepository workoutSubscriptionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<WorkoutSubscription> getAllWorkoutSubscriptions() {
        return workoutSubscriptionRepository.findAll();
    }

    @RequestMapping(value = "/addWorkoutSubscription", method = RequestMethod.POST)
    public void saveWorkoutSubscription(@RequestParam("days") int days,
                                        @RequestParam("price") Long price,
                                        @RequestParam("name") String name) {
        WorkoutSubscription workoutSubscription = new WorkoutSubscription(days, name, price);
        workoutSubscriptionRepository.save(workoutSubscription);
    }

}
