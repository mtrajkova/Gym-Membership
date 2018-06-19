package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.*;
import com.fitness.capitol.gym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        User user = userService.findByUsername(username);
        return user_specialSubscriptionService.getAllSpecialsByUser(user);
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    private void addSpecialSubscription(@RequestParam("months") int months,
                                        @RequestParam("name") String name,
                                        @RequestParam("price") Long price,
                                        @RequestParam("username") String username,
                                        @RequestParam("startDate") String start,
                                        @RequestParam("endDate") String end) {
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
        SpecialSubscription specialSubscription = new SpecialSubscription();
        specialSubscription.setDurationMonths(months);
        specialSubscription.setSuper(name, price);
        specialSubscription.setEndOfRegistration(endDate);
        specialSubscription.setStartOfRegistration(startDate);
        specialSubscriptionService.save(specialSubscription);
        User user = userService.findByUsername(username);
        user_specialSubscriptionService.save(specialSubscription, user);
    }
}
