package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.persistance.User_SpecialSubscriptionRepository;
import com.fitness.capitol.gym.service.User_SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_SpecialSubscriptionServiceImpl implements User_SpecialSubscriptionService {
    @Autowired
    private User_SpecialSubscriptionRepository user_specialSubscriptionRepository;

    @Override
    public List<SpecialSubscription> getAllSpecialsByUser(User user) {
        return user_specialSubscriptionRepository.findAllByUser(user);
    }
}
