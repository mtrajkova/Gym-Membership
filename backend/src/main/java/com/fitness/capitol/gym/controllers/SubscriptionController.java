package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.persistance.NormalSubscriptionRepository;
import com.fitness.capitol.gym.persistance.SpecialSubscriptionRepository;
import com.fitness.capitol.gym.persistance.SubscriptionRepository;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private NormalSubscriptionRepository normalSubscriptionRepository;

    @Autowired
    private SpecialSubscriptionRepository specialSubscriptionRepository;

    @Autowired
    private WorkoutSubscriptionRepository workoutSubscriptionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Object> getAllSubscriptions() {
        List<Object> subscriptions = new ArrayList<>();
        subscriptions.addAll(specialSubscriptionRepository.findAll());
        subscriptions.addAll(normalSubscriptionRepository.findAll());
        subscriptions.addAll(workoutSubscriptionRepository.findAll());
        return subscriptions;
    }

}
