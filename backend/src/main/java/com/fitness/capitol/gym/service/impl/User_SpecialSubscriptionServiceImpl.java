package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.*;
import com.fitness.capitol.gym.persistance.User_SpecialSubscriptionRepository;
import com.fitness.capitol.gym.service.User_SpecialSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_SpecialSubscriptionServiceImpl implements User_SpecialSubscriptionService {
    @Autowired
    private User_SpecialSubscriptionRepository user_specialSubscriptionRepository;


    @Override
    public List<SpecialSubscription> getAllSpecialsByClient(Client client) {
        List<Client_SpecialSubscription> client_specialSubscriptions = user_specialSubscriptionRepository.findAllByClient(client);
        List<SpecialSubscription> specialSubscriptions = new ArrayList<>();
        for (Client_SpecialSubscription uws : client_specialSubscriptions) {
            specialSubscriptions.add(uws.getSpecialSubscription());
        }
        return specialSubscriptions;
    }

    @Override
    public void save(SpecialSubscription specialSubscription, Client client) {
        Client_SpecialSubscription client_specialSubscription = new Client_SpecialSubscription();
        client_specialSubscription.setClient(client);
        client_specialSubscription.setSpecialSubscription(specialSubscription);
        user_specialSubscriptionRepository.save(client_specialSubscription);
    }

    @Override
    public Client_SpecialSubscription findByClientAndSpecialSubscription(Client client, SpecialSubscription specialSubscription) {
        return user_specialSubscriptionRepository.findByClientAndSpecialSubscription(client, specialSubscription);
    }
}
