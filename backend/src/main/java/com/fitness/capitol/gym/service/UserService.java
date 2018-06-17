package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.excpetions.InvalidLoginCredentialsException;
import com.fitness.capitol.gym.excpetions.UserAlreadyExistsException;
import com.fitness.capitol.gym.excpetions.UserNotFoundException;
import com.fitness.capitol.gym.excpetions.UserParameterNotFoundException;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    void save(User user) throws UserParameterNotFoundException, UserAlreadyExistsException;

    User findByName(String name);

    User findByLoginCredentials(String username, String password) throws InvalidLoginCredentialsException, UserNotFoundException;
}
