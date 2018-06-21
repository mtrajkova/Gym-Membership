package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_WorkoutSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_WorkoutSubscriptionRepository extends JpaRepository<Client_WorkoutSubscription, Long> {
    List<Client_WorkoutSubscription> findAllByClient(Client client);
    Client_WorkoutSubscription findByClientAndWorkoutSubscription(Client client, WorkoutSubscription workoutSubscription);

    void deleteClient_WorkoutSubscriptionByClientAndWorkoutSubscription(Client client, WorkoutSubscription workoutSubscription);

}
