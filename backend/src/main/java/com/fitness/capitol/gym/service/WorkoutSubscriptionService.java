package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.WorkoutSubscription;

import java.util.List;

public interface WorkoutSubscriptionService {
    List<WorkoutSubscription> findAll();

    void save(WorkoutSubscription workoutSubscription);

    WorkoutSubscription findByName(String name);
}
