package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.persistance.SpecialSubscriptionRepository;
import com.fitness.capitol.gym.service.SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialSubscriptionServiceImpl implements SpecialSubscriptionService {
    @Autowired
    private SpecialSubscriptionRepository specialSubscriptionRepository;

    @Override
    public List<SpecialSubscription> findAll() {
        return specialSubscriptionRepository.findAll();
    }

    @Override
    public void save(SpecialSubscription specialSubscription) {
        specialSubscriptionRepository.save(specialSubscription);
    }
}
