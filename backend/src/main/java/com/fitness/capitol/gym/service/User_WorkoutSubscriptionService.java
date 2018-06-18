package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.*;

import java.util.List;

public interface User_WorkoutSubscriptionService {
    List<WorkoutSubscription> getAllWorkoutSubsByUser(User user);
    void save(WorkoutSubscription workoutSubscription, User user);

}
