package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.NormalSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormalSubscriptionRepository extends JpaRepository<NormalSubscription, Long> {
    List<NormalSubscription> findAll();
}
