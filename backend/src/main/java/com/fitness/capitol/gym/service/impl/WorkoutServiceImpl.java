package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.persistance.WorkoutRepository;
import com.fitness.capitol.gym.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Repository
//@Transactional
public class WorkoutServiceImpl implements WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public List<Workout> findAllByUser(User user) {
       return workoutRepository.findAllByUser(user);
    }

    @Override
//    @Transactional
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);

//        workoutRepository.saveAndFlush(workout);
    }

    @Override
    public List<Workout> findByUserId(Long id) {
        return workoutRepository.findByUserId(id);
    }

    @Override
    public Workout findById(Long id) {
        return workoutRepository.findById(id);
    }
}
