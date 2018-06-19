package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.User_SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/mySubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class User_SubscriptionController {
    @Autowired
    private User_SubscriptionService user_subscriptionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public List<Object> getMySubscriptions(@PathVariable("username") String username) {
        return user_subscriptionService.findAllByUser(userService.findByUsername(username));
    }


}
