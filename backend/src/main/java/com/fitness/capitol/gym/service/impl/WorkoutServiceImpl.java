package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.persistance.WorkoutRepository;
import com.fitness.capitol.gym.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Repository
public class WorkoutServiceImpl implements WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public List<Workout> findAllByClient(Client client) {
        return workoutRepository.findAllByClient(client);
    }

    @Override
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public List<Workout> findByUserId(Long id) {
        return workoutRepository.findByClientId(id);
    }

    @Override
    public Workout findById(Long id) {
        return workoutRepository.findById(id);
    }

    @Override
    public Workout findByDate(LocalDateTime date, Client client) {
        return workoutRepository.findByDateAndClient(date, client);
    }

    @Override
    public Workout findByDateAndWorkoutNameAndClient(LocalDateTime date, String workoutName, Client client) {
        return workoutRepository.findByDateAndWorkoutNameAndClient(date, workoutName, client);
    }

    @Override
    public List<Workout> findAllByDateAndClient(LocalDateTime date, Client client) {
        return workoutRepository.findAllByDateAndClient(date,client);
    }
}
