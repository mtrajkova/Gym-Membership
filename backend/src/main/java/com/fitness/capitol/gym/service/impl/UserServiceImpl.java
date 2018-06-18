package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.excpetions.InvalidLoginCredentialsException;
import com.fitness.capitol.gym.excpetions.UserAlreadyExistsException;
import com.fitness.capitol.gym.excpetions.UserNotFoundException;
import com.fitness.capitol.gym.excpetions.UserParameterNotFoundException;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.persistance.UserRepository;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) throws UserParameterNotFoundException, UserAlreadyExistsException {
        if (!checkUserParameters(user)) {
            throw new UserParameterNotFoundException("User parameters are not complete");
        } else if (userExists(user)) {
            throw new UserAlreadyExistsException("User already exists");
        } else {
            userRepository.save(user);
        }
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByLoginCredentials(String username, String password) throws InvalidLoginCredentialsException, UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (!checkLoginCredentials(user, password)) {
            throw new InvalidLoginCredentialsException("Invalid user credentials");
        } else if (user == null) {
            throw new UserNotFoundException("User was not found");
        } else {
            return user;
        }
    }

    private boolean checkUserParameters(User user) {
        return user.getUsername() != null &&
                user.getPassword() != null &&
                user.getPhone() != null &&
                user.getName() != null;
    }

    private boolean checkLoginCredentials(User user, String password) {
        return user.getPassword().equals(password);
    }

    private boolean userExists(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }

}
