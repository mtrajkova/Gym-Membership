package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.service.NormalSubscriptionService;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.User_NormalSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/myNormalSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class User_NormalSubscriptionController {
    @Autowired
    private User_NormalSubscriptionService user_normalSubscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private NormalSubscriptionService normalSubscriptionService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    private List<NormalSubscription> getMyNormals(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return user_normalSubscriptionService.getAllNormalsByUser(user);
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    private void addSpecialSubscription(@RequestParam("name") String name,
                                        @RequestParam("price") Long price,
                                        @RequestParam("months") int months,
                                        @RequestParam("username") String username) {


        NormalSubscription normalSubscription = new NormalSubscription();
        normalSubscription.setDurationMonths(months);
        normalSubscription.setSuper(name, price);
        normalSubscriptionService.save(normalSubscription);
        User user = userService.findByUsername(username);
        user_normalSubscriptionService.save(normalSubscription, user);
    }
}
