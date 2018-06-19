package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.*;
import com.fitness.capitol.gym.persistance.User_SpecialSubscriptionRepository;
import com.fitness.capitol.gym.service.User_SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_SpecialSubscriptionServiceImpl implements User_SpecialSubscriptionService {
    @Autowired
    private User_SpecialSubscriptionRepository user_specialSubscriptionRepository;


    @Override
    public List<SpecialSubscription> getAllSpecialsByUser(User user) {
        List<User_SpecialSubscription> user_specialSubscriptions = user_specialSubscriptionRepository.findAllByUser(user);
        List<SpecialSubscription> specialSubscriptions = new ArrayList<>();
        for (User_SpecialSubscription uws : user_specialSubscriptions) {
            specialSubscriptions.add(uws.getSpecialSubscription());
        }
        return specialSubscriptions;
    }

    @Override
    public void save(SpecialSubscription specialSubscription, User user) {
        User_SpecialSubscription user_specialSubscription = new User_SpecialSubscription();
        user_specialSubscription.setUser(user);
        user_specialSubscription.setSpecialSubscription(specialSubscription);
        user_specialSubscriptionRepository.save(user_specialSubscription);
    }
}
