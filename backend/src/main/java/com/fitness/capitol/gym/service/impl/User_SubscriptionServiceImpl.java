package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Subscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.persistance.User_SubscriptionRepository;
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
    public List<Object> findAllByUser(User user) {
        List<Object> subscriptions = new ArrayList<>();
        subscriptions.addAll(user_normalSubscriptionService.getAllNormalsByUser(user));
        subscriptions.addAll(user_specialSubscriptionService.getAllSpecialsByUser(user));
        subscriptions.addAll(user_workoutSubscriptionService.getAllWorkoutSubsByUser(user));
        return subscriptions;
    }

}
