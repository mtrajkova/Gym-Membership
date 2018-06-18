package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.SpecialSubscription;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_WorkoutSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_WorkoutSubscriptionRepository extends JpaRepository<User_WorkoutSubscription, Long> {
    List<User_WorkoutSubscription> findAllByUser(User user);

}
