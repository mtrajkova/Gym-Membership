package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

}
