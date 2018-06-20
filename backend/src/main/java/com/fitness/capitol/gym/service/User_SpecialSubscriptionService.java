package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.SpecialSubscription;

import java.util.List;

public interface User_SpecialSubscriptionService {
    List<SpecialSubscription> getAllSpecialsByClient(Client client);

    void save(SpecialSubscription specialSubscription, Client client);
}
