package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_WorkoutSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_WorkoutSubscriptionRepository extends JpaRepository<Client_WorkoutSubscription, Long> {
    List<Client_WorkoutSubscription> findAllByClient(Client client);

}
