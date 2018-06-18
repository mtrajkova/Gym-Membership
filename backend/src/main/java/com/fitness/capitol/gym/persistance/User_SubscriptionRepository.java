package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Subscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_SubscriptionRepository extends JpaRepository<User_Subscription, Long> {
    List<Subscription> findAllByUser(User user);
    List<User> findAllBySubscription(Subscription subscription);
}
