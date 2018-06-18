package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface WorkoutRepository extends JpaRepository<Workout,Long> {
    List<Workout> findAllByUser(User user);
    List<Workout> findByUserId(Long id);
    Workout findById(Long id);
}
