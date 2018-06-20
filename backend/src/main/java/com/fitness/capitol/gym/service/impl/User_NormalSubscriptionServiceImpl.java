package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.Client_NormalSubscription;
import com.fitness.capitol.gym.persistance.User_NormalSubscriptionRepository;
import com.fitness.capitol.gym.service.User_NormalSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_NormalSubscriptionServiceImpl implements User_NormalSubscriptionService {
    @Autowired
    private User_NormalSubscriptionRepository user_normalSubscriptionRepository;

    @Override
    public List<NormalSubscription> getAllNormalsByClient(Client client) {
        List<Client_NormalSubscription> client_normalSubscriptions = user_normalSubscriptionRepository.findAllByClient(client);
        List<NormalSubscription> normalSubscriptions = new ArrayList<>();
        for (Client_NormalSubscription uws : client_normalSubscriptions) {
            normalSubscriptions.add(uws.getNormalSubscription());
        }
        return normalSubscriptions;
    }

    @Override
    public void save(NormalSubscription normalSubscription, Client client) {
        Client_NormalSubscription client_normalSubscription = new Client_NormalSubscription();
        client_normalSubscription.setClient(client);
        client_normalSubscription.setNormalSubscription(normalSubscription);
        user_normalSubscriptionRepository.save(client_normalSubscription);
    }
}
