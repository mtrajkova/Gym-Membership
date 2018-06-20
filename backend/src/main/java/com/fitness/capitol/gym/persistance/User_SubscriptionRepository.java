package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Subscription;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_SubscriptionRepository extends JpaRepository<Client_Subscription, Long> {
    List<Subscription> findAllByClient(Client client);
    List<Client> findAllBySubscription(Subscription subscription);
}
