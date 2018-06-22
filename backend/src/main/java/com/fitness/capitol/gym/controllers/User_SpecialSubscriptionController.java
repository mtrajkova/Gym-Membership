package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.*;
import com.fitness.capitol.gym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/mySpecialSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class User_SpecialSubscriptionController {
    @Autowired
    private User_SpecialSubscriptionService user_specialSubscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private SpecialSubscriptionService specialSubscriptionService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    private List<SpecialSubscription> getMySpecials(@PathVariable("username") String username) {

        Client client = userService.findByUsername(username);
        return user_specialSubscriptionService.getAllSpecialsByClient(client);
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    private ResponseEntity addSpecialSubscription(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("username") String username) {
        SpecialSubscription specialSubscription = specialSubscriptionService.findByName(name);
        Client client = userService.findByUsername(username);

        if (specialSubscription == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This subscription doesn't exist");
        } else if (user_specialSubscriptionService.findByClientAndSpecialSubscription(client, specialSubscription) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have already subscribed to this subscription!");
        } else if (client.getCredits() < price) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough credits!");
        } else {
            client.setCredits((int) (client.getCredits() - price));
            user_specialSubscriptionService.save(specialSubscription, client);
            return ResponseEntity.status(HttpStatus.OK).body("Subscribed!");
        }
        /*String[] parts = start.split("\\.");
        Date startDate = new Date();
        long time = 0;
        time = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]);
        startDate.setTime(time);
        parts = end.split("\\.");
        Date endDate = new Date();
        long time2 = 0;
        time2 = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]);
        endDate.setTime(time2);
        SpecialSubscription specialSubscription = new SpecialSubscription();
        specialSubscription.setDurationMonths(months);
        specialSubscription.setSuper(name, price);
        specialSubscription.setEndOfRegistration(endDate);
        specialSubscription.setStartOfRegistration(startDate);
        specialSubscriptionService.save(specialSubscription);*/

    }
}
