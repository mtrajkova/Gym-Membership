package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.SpecialSubscription;

import java.util.List;

public interface SpecialSubscriptionService {
    List<SpecialSubscription> findAll();
    void save(SpecialSubscription specialSubscription);
}
