package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    void save(User user);
}
