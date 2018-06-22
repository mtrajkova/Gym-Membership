package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.NormalSubscriptionRepository;
import com.fitness.capitol.gym.persistance.SpecialSubscriptionRepository;
import com.fitness.capitol.gym.persistance.SubscriptionRepository;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import com.fitness.capitol.gym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /*@Autowired
    private NormalSubscriptionRepository normalSubscriptionRepository;

    @Autowired
    private SpecialSubscriptionRepository specialSubscriptionRepository;

    @Autowired
    private WorkoutSubscriptionRepository workoutSubscriptionRepository;*/

    @Autowired
    private SpecialSubscriptionService specialSubscriptionService;

    @Autowired
    private NormalSubscriptionService normalSubscriptionService;

    @Autowired
    private WorkoutSubscriptionService workoutSubscriptionService;

    @Autowired
    private User_WorkoutSubscriptionService user_workoutSubscriptionService;

    @Autowired
    private User_NormalSubscriptionService user_normalSubscriptionService;

    @Autowired
    private User_SpecialSubscriptionService user_specialSubscriptionService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Object> getAllSubscriptions() {
        List<Object> subscriptions = new ArrayList<>();
        subscriptions.addAll(specialSubscriptionService.findAll());
        subscriptions.addAll(normalSubscriptionService.findAll());
        subscriptions.addAll(workoutSubscriptionService.findAll());
        return subscriptions;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/forUsers")
    public List<Object> getAll() {
        List<Object> subscriptions = new ArrayList<>();
        for (SpecialSubscription specialSubscription : specialSubscriptionService.findAll()) {
            if (specialSubscription.isAvailable())
                subscriptions.add(specialSubscription);
        }
        for (WorkoutSubscription workoutSubscription : workoutSubscriptionService.findAll()) {
            if (workoutSubscription.isAvailable())
                subscriptions.add(workoutSubscription);
        }
        for (NormalSubscription normalSubscription : normalSubscriptionService.findAll()) {
            if (normalSubscription.isAvailable())
                subscriptions.add(normalSubscription);
        }
        return subscriptions;
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    public ResponseEntity addWorkout(@RequestParam("username") String username, @RequestParam("name") String name) {
        WorkoutSubscription workoutSubscription = workoutSubscriptionService.findByName(name);
        SpecialSubscription specialSubscription = specialSubscriptionService.findByName(name);
        NormalSubscription normalSubscription = normalSubscriptionService.findByName(name);
        Client client = userService.findByUsername(username);

        if (workoutSubscription != null) {
            if (user_workoutSubscriptionService.findByClientAndWorkoutSubscription(client, workoutSubscription) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have already subscribed to this subscription!");
            } else if (client.getCredits() < workoutSubscription.getPrice()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You don't have enough credits!");
            } else {
                user_workoutSubscriptionService.save(workoutSubscription, client);
                client.setCredits((int) (client.getCredits() - workoutSubscription.getPrice()));
                return ResponseEntity.status(HttpStatus.OK).body("Subscribed!");
            }
        } else if (specialSubscription != null) {
            if (user_specialSubscriptionService.findByClientAndSpecialSubscription(client, specialSubscription) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have already subscribed to this subscription!");
            } else if (client.getCredits() < specialSubscription.getPrice()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough credits!");
            } else {
                client.setCredits((int) (client.getCredits() - specialSubscription.getPrice()));
                user_specialSubscriptionService.save(specialSubscription, client);
                return ResponseEntity.status(HttpStatus.OK).body("Subscribed!");
            }
        } else if (normalSubscription != null) {
            if (user_normalSubscriptionService.findByClientAndNormalSubscription(client, normalSubscription) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription has already been taken!");
            } else {
                if (client.getCredits() < normalSubscription.getPrice()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough credits!");
                } else {
                    client.setCredits((int) (client.getCredits() - normalSubscription.getPrice()));
                    user_normalSubscriptionService.save(normalSubscription, client);
                    return ResponseEntity.status(HttpStatus.OK).body("Subscribed!");
                }
            }
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown command");
    }

}
