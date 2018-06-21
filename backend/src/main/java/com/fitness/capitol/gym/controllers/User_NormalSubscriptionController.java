package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_NormalSubscription;
import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.service.NormalSubscriptionService;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.User_NormalSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        /*Client client = userService.findByUsername(username);
        List<NormalSubscription> normalSubscriptions = new ArrayList<>();
        for (Client_NormalSubscription client_normalSubscription : user_normalSubscriptionService.getNormalSubsAndClientByClient(client)) {
            LocalDateTime date = client_normalSubscription.getDateStarted();

            int months = client_normalSubscription.getNormalSubscription().getDurationMonths();
            LocalDateTime now = LocalDateTime.now();
            if (now.getYear() == date.getYear()) {
                if (now.getMonthValue() <= date.getMonthValue()) {
                    if (now.getMonthValue() == date.getMonthValue()) {
                        if (now.getDayOfMonth() <= date.getDayOfMonth()) {
                            normalSubscriptions.add(client_normalSubscription.getNormalSubscription());
                        }
                    } else normalSubscriptions.add(client_normalSubscription.getNormalSubscription());
                }
            }
        }*/


        return user_normalSubscriptionService.getAllNormalsByClient(userService.findByUsername(username));
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    private ResponseEntity addSpecialSubscription(@RequestParam("name") String name,
                                                  @RequestParam("username") String username) {

        Client client = userService.findByUsername(username);
        NormalSubscription normalSubscription = normalSubscriptionService.findByName(name);
        if (normalSubscription == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription doesn't exist");
        } else if (user_normalSubscriptionService.findByClientAndNormalSubscription(client, normalSubscription) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription has already been taken!");
        }
//        NormalSubscription normalSubscription = new NormalSubscription();
//        normalSubscription.setDurationMonths(months);
//        normalSubscription.setSuper(name, price);
//        normalSubscriptionService.save(normalSubscription);
        else {
            if (client.getCredits() < normalSubscription.getPrice()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough credits!");
            } else {
                client.setCredits((int) (client.getCredits() - normalSubscription.getPrice()));
                user_normalSubscriptionService.save(normalSubscription, client);
                return ResponseEntity.status(HttpStatus.OK).body("Subscribed!");
            }
        }

    }
}
