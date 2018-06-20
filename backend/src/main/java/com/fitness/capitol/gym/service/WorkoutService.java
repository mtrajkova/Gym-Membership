package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Workout;

import java.util.Date;
import java.util.List;

public interface WorkoutService {
    List<Workout> findAllByClient(Client client);

    Workout save(Workout workout);

    List<Workout> findByUserId(Long id);

    Workout findById(Long id);

    Workout findByDate(Date date, Client client);
}
