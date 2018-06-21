package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_WorkoutSubscription;
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
    public List<WorkoutSubscription> getAllWorkoutSubsByClient(Client client) {
        List<Client_WorkoutSubscription> client_workoutSubscription = user_workoutSubscriptionRepository.findAllByClient(client);
        List<WorkoutSubscription> workoutSubscriptions = new ArrayList<>();
        for(Client_WorkoutSubscription uws: client_workoutSubscription){
            workoutSubscriptions.add(uws.getWorkoutSubscription());
        }
        return  workoutSubscriptions;
    }


    @Override
    public void save(WorkoutSubscription workoutSubscription, Client client) {
        Client_WorkoutSubscription client_workoutSubscription = new Client_WorkoutSubscription();
        client_workoutSubscription.setClient(client);
        client_workoutSubscription.setWorkoutSubscription(workoutSubscription);
        user_workoutSubscriptionRepository.save(client_workoutSubscription);
    }

    @Override
    public Client_WorkoutSubscription findByClientAndWorkoutSubscription(Client client, WorkoutSubscription workoutSubscription) {
        return user_workoutSubscriptionRepository.findByClientAndWorkoutSubscription(client,workoutSubscription);
    }

    @Override
    public void deleteClient_WorkoutSubscriptionByClientAndWorkoutSubscription(Client client, WorkoutSubscription workoutSubscription) {
        user_workoutSubscriptionRepository.deleteClient_WorkoutSubscriptionByClientAndWorkoutSubscription(client,workoutSubscription);
    }
}
