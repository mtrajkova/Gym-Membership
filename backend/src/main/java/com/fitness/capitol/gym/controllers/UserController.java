package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@SessionAttributes("user")

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public Client getUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }


}
