package com.fitness.capitol.gym.controllers;


import com.fitness.capitol.gym.excpetions.InvalidLoginCredentialsException;
import com.fitness.capitol.gym.excpetions.UserAlreadyExistsException;
import com.fitness.capitol.gym.excpetions.UserParameterNotFoundException;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginRegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) throws InvalidLoginCredentialsException {
        User user = userService.findByUsername(username);
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new InvalidLoginCredentialsException("Wrong login credentials");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phone") String phone, @RequestParam("name") String name) throws UserParameterNotFoundException, UserAlreadyExistsException {

        User user = userService.findByUsername(username);
        if (!checkUserParameters(username, password, phone, name)) {
            throw new UserParameterNotFoundException("User parameters are not complete");
        } else if (!userExists(user)) {
            throw new UserAlreadyExistsException("User already exists");
        } else {
            User newUser = new User();
            newUser.setName(name);
            newUser.setDateJoined(LocalDateTime.now());
            newUser.setPassword(password);
            newUser.setUsername(username);
            newUser.setAdmin(false);
            newUser.setPhone(phone);
            userService.save(newUser);
        }
    }

    private boolean checkUserParameters(String username, String password, String phone, String name) {
        return username != null &&
                password != null &&
                phone != null &&
                name != null;
    }

    private boolean userExists(User user) {
        return user != null;
    }

}
