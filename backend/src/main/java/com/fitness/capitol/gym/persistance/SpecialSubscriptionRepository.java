package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.SpecialSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialSubscriptionRepository extends JpaRepository<SpecialSubscription, Long> {
    List<SpecialSubscription> findAll();
}
