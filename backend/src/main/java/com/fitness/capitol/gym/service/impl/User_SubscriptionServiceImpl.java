package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.service.User_NormalSubscriptionService;
import com.fitness.capitol.gym.service.User_SpecialSubscriptionService;
import com.fitness.capitol.gym.service.User_SubscriptionService;
import com.fitness.capitol.gym.service.User_WorkoutSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_SubscriptionServiceImpl implements User_SubscriptionService {

    @Autowired
    private User_SpecialSubscriptionService user_specialSubscriptionService;

    @Autowired
    private User_WorkoutSubscriptionService user_workoutSubscriptionService;

    @Autowired
    private User_NormalSubscriptionService user_normalSubscriptionService;

    @Override
    public List<Object> findAllByClient(Client client) {
        List<Object> subscriptions = new ArrayList<>();
        subscriptions.addAll(user_normalSubscriptionService.getAllNormalsByClient(client));
        subscriptions.addAll(user_specialSubscriptionService.getAllSpecialsByClient(client));
        subscriptions.addAll(user_workoutSubscriptionService.getAllWorkoutSubsByClient(client));
        return subscriptions;
    }

}
