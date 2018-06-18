package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.persistance.NormalSubscriptionRepository;
import com.fitness.capitol.gym.service.NormalSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormalSubscriptionServiceImpl implements NormalSubscriptionService {
    @Autowired
    private NormalSubscriptionRepository normalSubscriptionRepository;

    @Override
    public List<NormalSubscription> findAll() {
        return normalSubscriptionRepository.findAll();
    }
}
