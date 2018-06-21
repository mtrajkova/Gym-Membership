package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.*;

import java.util.List;

public interface User_WorkoutSubscriptionService {
    List<WorkoutSubscription> getAllWorkoutSubsByClient(Client client);

    void save(WorkoutSubscription workoutSubscription, Client client);

    Client_WorkoutSubscription findByClientAndWorkoutSubscription(Client client, WorkoutSubscription workoutSubscription);

    void deleteClient_WorkoutSubscriptionByClientAndWorkoutSubscription(Client client, WorkoutSubscription workoutSubscription);

}
