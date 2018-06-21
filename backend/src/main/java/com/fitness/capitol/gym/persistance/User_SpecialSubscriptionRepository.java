package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_SpecialSubscription;
import com.fitness.capitol.gym.model.SpecialSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_SpecialSubscriptionRepository extends JpaRepository<Client_SpecialSubscription, Long> {
    List<Client_SpecialSubscription> findAllByClient(Client client);

    Client_SpecialSubscription findByClientAndSpecialSubscription(Client client, SpecialSubscription specialSubscription);
}
