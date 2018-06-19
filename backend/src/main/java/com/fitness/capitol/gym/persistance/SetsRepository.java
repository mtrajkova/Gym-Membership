package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Sets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetsRepository extends JpaRepository<Sets, Long> {
    List<Sets> findAllByExercise(Exercise exercise);
}
