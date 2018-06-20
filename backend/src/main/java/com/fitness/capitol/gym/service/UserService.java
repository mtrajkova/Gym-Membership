package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.excpetions.InvalidLoginCredentialsException;
import com.fitness.capitol.gym.excpetions.UserAlreadyExistsException;
import com.fitness.capitol.gym.excpetions.UserNotFoundException;
import com.fitness.capitol.gym.excpetions.UserParameterNotFoundException;
import com.fitness.capitol.gym.model.Client;

import java.util.List;

public interface UserService {
    List<Client> findAll();

    Client findById(Long id);

    Client findByUsername(String username);

    void save(Client client) throws UserParameterNotFoundException, UserAlreadyExistsException;

    Client findByName(String name);

    Client findByLoginCredentials(String username, String password) throws InvalidLoginCredentialsException, UserNotFoundException;
}
