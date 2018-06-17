package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Exercise;

public interface ExerciseService {
    Exercise findByName(String name);
}
