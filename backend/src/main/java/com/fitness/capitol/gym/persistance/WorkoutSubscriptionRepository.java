package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSubscriptionRepository extends JpaRepository<WorkoutSubscription,Long> {
    List<WorkoutSubscription> findAll();
}
