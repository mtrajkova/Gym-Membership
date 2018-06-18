package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> findAllByName(String name);
}
