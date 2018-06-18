package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    List<Subscription> findAllByName(String name);

}
