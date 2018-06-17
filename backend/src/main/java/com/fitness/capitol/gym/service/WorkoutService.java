package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;

import java.util.List;

public interface WorkoutService {
    List<Workout> findAllByUser(User user);
    void save(Workout workout);
    List<Workout> findByUserId(Long id);
    Workout findById(Long id);
}
