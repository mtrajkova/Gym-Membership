package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.service.NormalSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/normalSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class NormalSubscriptionController {
    @Autowired
    private NormalSubscriptionService normalSubscriptionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<NormalSubscription> getAllNormals() {
        return normalSubscriptionService.findAll();
    }

    @RequestMapping(value = "/addNormalSubscription", method = RequestMethod.POST)
    public ResponseEntity addNormalSubscription(@RequestParam("name") String name, @RequestParam("price") Long price, @RequestParam("durationMonths") int durationMonths) {
        NormalSubscription normalSubscription = normalSubscriptionService.findByName(name);
        if (normalSubscription != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This subscription already exists!");
        } else {
            normalSubscription.setDurationMonths(durationMonths);
            normalSubscription.setName(name);
            normalSubscription.setPrice(price);
            normalSubscriptionService.save(normalSubscription);
            return ResponseEntity.status(HttpStatus.OK).body("Subscription saved!");
        }
    }
}
