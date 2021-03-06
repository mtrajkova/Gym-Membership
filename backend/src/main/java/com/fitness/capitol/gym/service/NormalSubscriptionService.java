package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.NormalSubscription;

import java.util.List;

public interface NormalSubscriptionService {
    List<NormalSubscription> findAll();

    void save(NormalSubscription normalSubscription);

    NormalSubscription findByName(String name);
}

