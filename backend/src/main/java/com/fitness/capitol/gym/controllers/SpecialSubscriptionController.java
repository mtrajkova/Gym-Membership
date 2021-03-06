package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.service.SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity saveWorkoutSubscription(@RequestParam("months") int months,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("price") Long price,
                                                  @RequestParam("startDate") String start,
                                                  @RequestParam("endDate") String end) {
        SpecialSubscription specialSubscription = specialSubscriptionService.findByName(name);
        if (specialSubscription != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This subscription already exists!");
        } else {
            specialSubscription = new SpecialSubscription();
            specialSubscription = new SpecialSubscription(start, end, months, name, price);
            specialSubscriptionService.save(specialSubscription);

            return ResponseEntity.status(HttpStatus.OK).body("Subscription saved!");
        }
    }

    @RequestMapping(value = "/changeAvailability", method = RequestMethod.POST)
    public ResponseEntity changeAvailability(@RequestParam("isAvailable") boolean isAvailable, @RequestParam("name") String name) {
        SpecialSubscription specialSubscription = specialSubscriptionService.findByName(name);

        specialSubscription.setAvailable(!isAvailable);
        specialSubscriptionService.save(specialSubscription);
        return ResponseEntity.status(HttpStatus.OK).body("Availability changed!");

    }

    @RequestMapping(value = "/forUsers", method = RequestMethod.GET)
    public List<SpecialSubscription> getWorkoutSubsForUsers() {
        List<SpecialSubscription> specialSubscriptions = new ArrayList<>();

        for (SpecialSubscription specialSubscription : specialSubscriptionService.findAll()) {
            if (specialSubscription.isAvailable())
                specialSubscriptions.add(specialSubscription);
        }

        return specialSubscriptions;
    }
}
