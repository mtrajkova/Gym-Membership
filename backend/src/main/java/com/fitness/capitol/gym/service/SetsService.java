package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Sets;

import java.util.List;

public interface SetsService {
    void save(Sets sets);

    List<Sets> findAllByExercise(Exercise exercise);
}
