package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Exercise_WorkoutRepository extends JpaRepository<Workout_Exercise,Long> {
    List<Exercise> findAllByWorkoutId(Long id);
}
