package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_NormalSubscription;
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
    public List<NormalSubscription> getAllNormalsByUser(User user) {
        List<User_NormalSubscription> user_normalSubscriptions = user_normalSubscriptionRepository.findAllByUser(user);
        List<NormalSubscription> normalSubscriptions = new ArrayList<>();
        for (User_NormalSubscription uws : user_normalSubscriptions) {
            normalSubscriptions.add(uws.getNormalSubscription());
        }
        return normalSubscriptions;
    }

    @Override
    public void save(NormalSubscription normalSubscription, User user) {
        User_NormalSubscription user_normalSubscription = new User_NormalSubscription();
        user_normalSubscription.setUser(user);
        user_normalSubscription.setNormalSubscription(normalSubscription);
        user_normalSubscriptionRepository.save(user_normalSubscription);
    }
}
