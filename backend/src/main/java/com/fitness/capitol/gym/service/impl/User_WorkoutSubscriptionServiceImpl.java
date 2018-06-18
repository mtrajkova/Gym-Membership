package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_WorkoutSubscription;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.persistance.User_WorkoutSubscriptionRepository;
import com.fitness.capitol.gym.service.User_WorkoutSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_WorkoutSubscriptionServiceImpl implements User_WorkoutSubscriptionService {
    @Autowired
    private User_WorkoutSubscriptionRepository user_workoutSubscriptionRepository;

    @Override
    public List<WorkoutSubscription> getAllWorkoutSubsByUser(User user) {
        List<User_WorkoutSubscription> user_workoutSubscription = user_workoutSubscriptionRepository.findAllByUser(user);
        List<WorkoutSubscription> workoutSubscriptions = new ArrayList<>();
        for(User_WorkoutSubscription uws: user_workoutSubscription){
            workoutSubscriptions.add(uws.getWorkoutSubscription());
        }
        return  workoutSubscriptions;
    }


    @Override
    public void save(WorkoutSubscription workoutSubscription, User user) {
        User_WorkoutSubscription user_workoutSubscription = new User_WorkoutSubscription();
        user_workoutSubscription.setUser(user);
        user_workoutSubscription.setWorkoutSubscription(workoutSubscription);
        user_workoutSubscriptionRepository.save(user_workoutSubscription);
    }
}
