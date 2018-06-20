package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();

    Client findById(Long id);

    Client findByUsername(String username);

    Client findByName(String name);
}
