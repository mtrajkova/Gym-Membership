package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.NormalSubscription;
import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_NormalSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_NormalSubscriptionRepository extends JpaRepository<User_NormalSubscription, Long> {
    List<User_NormalSubscription> findAllByUser(User user);

}
