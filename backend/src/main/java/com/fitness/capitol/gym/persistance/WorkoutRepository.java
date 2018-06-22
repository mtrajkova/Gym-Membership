package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Transactional
public interface WorkoutRepository extends JpaRepository<Workout,Long> {
    List<Workout> findAllByClient(Client client);
    List<Workout> findByClientId(Long id);
    Workout findById(Long id);
    Workout findByDateAndClient(LocalDateTime date, Client client);
    Workout findByDateAndWorkoutNameAndClient(LocalDateTime date, String workoutName, Client client);
    List<Workout> findAllByDateAndClient(LocalDateTime date, Client client);
}
