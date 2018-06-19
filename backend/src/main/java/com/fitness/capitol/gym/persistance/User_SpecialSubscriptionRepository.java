package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_SpecialSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_SpecialSubscriptionRepository extends JpaRepository<User_SpecialSubscription,Long> {
    List<User_SpecialSubscription> findAllByUser(User user);

}
