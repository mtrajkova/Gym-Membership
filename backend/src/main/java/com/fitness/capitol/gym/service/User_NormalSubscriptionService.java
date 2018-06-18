package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface User_NormalSubscriptionService {
    List<NormalSubscription> getAllNormalsByUser(User user);

}
