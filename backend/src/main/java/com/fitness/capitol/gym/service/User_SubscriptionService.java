package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Subscription;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface User_SubscriptionService {
    List<Object> findAllByUser(User user);

}
