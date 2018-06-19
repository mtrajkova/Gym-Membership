package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface User_SpecialSubscriptionService {
    List<SpecialSubscription> getAllSpecialsByUser(User user);

    void save(SpecialSubscription specialSubscription, User user);
}
