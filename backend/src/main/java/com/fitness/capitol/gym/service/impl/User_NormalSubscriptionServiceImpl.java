package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.persistance.User_NormalSubscriptionRepository;
import com.fitness.capitol.gym.service.User_NormalSubscriptionService;
import com.fitness.capitol.gym.service.User_SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_NormalSubscriptionServiceImpl implements User_NormalSubscriptionService {
    @Autowired
    private User_NormalSubscriptionRepository user_normalSubscriptionRepository;

    @Override
    public List<NormalSubscription> getAllNormalsByUser(User user) {
        return user_normalSubscriptionRepository.findAllByUser(user);
    }
}
