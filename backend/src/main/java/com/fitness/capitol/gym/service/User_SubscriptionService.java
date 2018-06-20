package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Client;

import java.util.List;

public interface User_SubscriptionService {
    List<Object> findAllByClient(Client client);

}
