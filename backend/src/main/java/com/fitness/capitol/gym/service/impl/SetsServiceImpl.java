package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Sets;
import com.fitness.capitol.gym.persistance.SetsRepository;
import com.fitness.capitol.gym.service.SetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetsServiceImpl implements SetsService {
    @Autowired
    private SetsRepository setsRepository;

    @Override
    public void save(Sets sets) {
        setsRepository.save(sets);
    }

    @Override
    public List<Sets> findAllByExercise(Exercise exercise) {
        return setsRepository.findAllByExercise(exercise);
    }
}
