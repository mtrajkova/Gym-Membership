package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import com.fitness.capitol.gym.service.WorkoutSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/workoutSubscriptions")
public class WorkoutSubscriptionController {

    @Autowired
    private WorkoutSubscriptionService workoutSubscriptionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<WorkoutSubscription> getAllWorkoutSubscriptions() {
        return workoutSubscriptionService.findAll();
    }

    @RequestMapping(value = "/addWorkoutSubscription", method = RequestMethod.POST)
    public ResponseEntity saveWorkoutSubscription(@RequestParam("days") int days,
                                                  @RequestParam("price") Long price,
                                                  @RequestParam("name") String name) {

        WorkoutSubscription workoutSubscription = workoutSubscriptionService.findByName(name);
        if (workoutSubscription != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This subscription already exists!");
        } else {
            workoutSubscription = new WorkoutSubscription(days, name, price);
            workoutSubscriptionService.save(workoutSubscription);
            return ResponseEntity.status(HttpStatus.OK).body("Subscription saved!");
        }
    }

    @RequestMapping(value = "/changeAvailability", method = RequestMethod.POST)
    public ResponseEntity changeAvailability(@RequestParam("isAvailable") boolean isAvailable, @RequestParam("name") String name) {
        WorkoutSubscription workoutSubscription = workoutSubscriptionService.findByName(name);

        workoutSubscription.setAvailable(!isAvailable);
        workoutSubscriptionService.save(workoutSubscription);
        return ResponseEntity.status(HttpStatus.OK).body("Availability changed!");

    }

    @RequestMapping(value = "/forUsers", method = RequestMethod.GET)
    public List<WorkoutSubscription> getWorkoutSubsForUsers() {
        List<WorkoutSubscription> workoutSubscriptions = new ArrayList<>();

        for (WorkoutSubscription workoutSubscription : workoutSubscriptionService.findAll()) {
            if (workoutSubscription.isAvailable())
                workoutSubscriptions.add(workoutSubscription);
        }

        return workoutSubscriptions;
    }

}
