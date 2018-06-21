package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_NormalSubscription;
import com.fitness.capitol.gym.model.NormalSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_NormalSubscriptionRepository extends JpaRepository<Client_NormalSubscription, Long> {
    List<Client_NormalSubscription> findAllByClient(Client client);
    Client_NormalSubscription findByClientAndNormalSubscription(Client client, NormalSubscription normalSubscription);

}
