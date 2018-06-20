package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.excpetions.InvalidLoginCredentialsException;
import com.fitness.capitol.gym.excpetions.UserAlreadyExistsException;
import com.fitness.capitol.gym.excpetions.UserNotFoundException;
import com.fitness.capitol.gym.excpetions.UserParameterNotFoundException;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.persistance.UserRepository;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Client> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Client findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(Client client) throws UserParameterNotFoundException, UserAlreadyExistsException {
        if (!checkUserParameters(client)) {
            throw new UserParameterNotFoundException("Client parameters are not complete");
        } else if (userExists(client)) {
            throw new UserAlreadyExistsException("Client already exists");
        } else {
            userRepository.save(client);
        }
    }

    @Override
    public Client findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Client findByLoginCredentials(String username, String password) throws InvalidLoginCredentialsException, UserNotFoundException {
        Client client = userRepository.findByUsername(username);
        if (!checkLoginCredentials(client, password)) {
            throw new InvalidLoginCredentialsException("Invalid client credentials");
        } else if (client == null) {
            throw new UserNotFoundException("Client was not found");
        } else {
            return client;
        }
    }

    private boolean checkUserParameters(Client client) {
        return client.getUsername() != null &&
                client.getPassword() != null &&
                client.getPhone() != null &&
                client.getName() != null;
    }

    private boolean checkLoginCredentials(Client client, String password) {
        return client.getPassword().equals(password);
    }

    private boolean userExists(Client client) {
        return userRepository.findByUsername(client.getUsername()) != null;
    }

}
