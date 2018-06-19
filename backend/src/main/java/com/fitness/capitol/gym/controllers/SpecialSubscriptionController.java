package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import com.fitness.capitol.gym.service.SpecialSubscriptionService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/specialSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecialSubscriptionController {
    @Autowired
    private SpecialSubscriptionService specialSubscriptionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<SpecialSubscription> getAllWorkoutSubscriptions() {
        return specialSubscriptionService.findAll();
    }

    @RequestMapping(value = "/addSpecialSubscription", method = RequestMethod.POST)
    public void saveWorkoutSubscription(@RequestParam("months") int months, @RequestParam("name") String name, @RequestParam("price") Long price, @RequestParam("startDate") String start, @RequestParam("endDate") String end) {
        String[] parts = start.split("\\.");
        Date startDate = new Date();
        long time = 0;
        time = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]);
        startDate.setTime(time);
        parts = end.split("\\.");
        Date endDate = new Date();
        long time2 = 0;
        time2 = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]);
        endDate.setTime(time2);
        SpecialSubscription specialSubscription = new SpecialSubscription(startDate, endDate, months, name, price);
        specialSubscriptionService.save(specialSubscription);
    }
}
