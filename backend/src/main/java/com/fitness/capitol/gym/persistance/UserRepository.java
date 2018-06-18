package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    User findByName(String name);
}
