package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Client_NormalSubscription;
import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.Client;

import java.util.List;

public interface User_NormalSubscriptionService {
    List<NormalSubscription> getAllNormalsByClient(Client client);

    void save(NormalSubscription normalSubscription, Client client);

    Client_NormalSubscription findByClientAndNormalSubscription(Client client, NormalSubscription normalSubscription);

}
