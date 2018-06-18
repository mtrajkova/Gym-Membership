package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Subscription;
import com.fitness.capitol.gym.persistance.SubscriptionRepository;
import com.fitness.capitol.gym.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> findAllByName(String name) {
        return subscriptionRepository.findAllByName(name);
    }
}
