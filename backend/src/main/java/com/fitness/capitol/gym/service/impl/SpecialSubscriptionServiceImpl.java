package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.persistance.SpecialSubscriptionRepository;
import com.fitness.capitol.gym.service.SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialSubscriptionServiceImpl implements SpecialSubscriptionService {
    @Autowired
    private SpecialSubscriptionRepository specialSubscriptionRepository;

    @Override
    public List<SpecialSubscription> findAll() {
        List<SpecialSubscription> specialSubscriptions = new ArrayList<>();
        for (SpecialSubscription specialSubscription : specialSubscriptionRepository.findAll()) {
            if (specialSubscription.isAvailable())
                specialSubscriptions.add(specialSubscription);
        }
        return specialSubscriptions;
    }

    @Override
    public void save(SpecialSubscription specialSubscription) {
        specialSubscriptionRepository.save(specialSubscription);
    }

    @Override
    public SpecialSubscription findByName(String name) {
        return specialSubscriptionRepository.findByName(name);
    }


}
