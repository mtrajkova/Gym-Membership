package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity saveWorkoutSubscription(@RequestParam("days") int days,
                                                  @RequestParam("price") Long price,
                                                  @RequestParam("name") String name) {

        WorkoutSubscription workoutSubscription = workoutSubscriptionRepository.findByName(name);
        if (workoutSubscription != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This subscription already exists!");
        } else {
            workoutSubscription = new WorkoutSubscription(days, name, price);
            workoutSubscriptionRepository.save(workoutSubscription);
            return ResponseEntity.status(HttpStatus.OK).body("Subscription saved!");
        }
    }

}
