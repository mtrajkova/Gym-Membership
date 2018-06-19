package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface WorkoutService {
    List<Workout> findAllByUser(User user);

    Workout save(Workout workout);

    List<Workout> findByUserId(Long id);

    Workout findById(Long id);

    Workout findByDate(Date date, User user);
}
