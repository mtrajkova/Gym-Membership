package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.persistance.ExerciseRepository;
import com.fitness.capitol.gym.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise findByName(String name) {
        return exerciseRepository.findByName(name);
    }
}
