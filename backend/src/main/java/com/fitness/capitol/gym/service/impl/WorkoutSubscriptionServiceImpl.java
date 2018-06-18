package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.WorkoutSubscriptionRepository;
import com.fitness.capitol.gym.service.WorkoutSubscriptionService;
import com.fitness.capitol.gym.service.Workout_ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutSubscriptionServiceImpl implements WorkoutSubscriptionService {
    @Autowired
    private WorkoutSubscriptionRepository workoutSubscriptionRepository;

    @Override
    public List<WorkoutSubscription> findAll() {
        return workoutSubscriptionRepository.findAll();
    }

    @Override
    public void save(WorkoutSubscription workoutSubscription) {
        workoutSubscriptionRepository.save(workoutSubscription);
    }
}
