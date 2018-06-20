package com.fitness.capitol.gym.controllers;


import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class LoginRegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        Client client;
        try {
            client = userService.findByLoginCredentials(username, password);
            return ResponseEntity.status(HttpStatus.OK).body(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("name") String name) {
        Client client = new Client();
        client.setUsername(username);
        client.setPassword(password);
        client.setPhone(phone);
        client.setName(name);
        client.setDateJoined(LocalDateTime.now());
        try {
            userService.save(client);
            return ResponseEntity.status(HttpStatus.OK).body("Client saved");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
