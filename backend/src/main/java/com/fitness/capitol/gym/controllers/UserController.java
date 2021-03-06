package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.excpetions.UserAlreadyExistsException;
import com.fitness.capitol.gym.excpetions.UserParameterNotFoundException;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/{username}/addCredits", method = RequestMethod.POST)
    public ResponseEntity addCredits(@PathVariable("username") String username, @RequestParam("credits") int credits, @RequestParam("usernameAdmin") String usernameAdmin) throws UserParameterNotFoundException, UserAlreadyExistsException {
        Client admin = userService.findByUsername(usernameAdmin);
        if(admin.isAdmin()) {
            Client client = userService.findByUsername(username);
            client.setCredits(client.getCredits() + credits);
            userService.save(client);
            return ResponseEntity.status(HttpStatus.OK).body("Credits added!");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are not an admin!");
        }

    }

    @RequestMapping(value = "/makeAdmin", method = RequestMethod.POST)
    public void makeAdmin(@RequestParam("from") String usernameFrom, @RequestParam("for") String usernameFor) throws UserParameterNotFoundException, UserAlreadyExistsException {
        Client admin = userService.findByUsername(usernameFrom);
        Client user = userService.findByUsername(usernameFor);
        if (admin.isAdmin()) {
            user.setAdmin(true);
        }
        userService.save(user);
    }

    @RequestMapping(value = "/removeAdmin", method = RequestMethod.PATCH)
    public ResponseEntity removeAdmin(@RequestParam("from") String usernameFrom, @RequestParam("for") String usernameFor,
                            @RequestParam("newCredits")Optional<Integer> newCredits,
                            @RequestParam("newAdmin") Optional<Boolean> newAdmin,
                            @RequestParam("newName") Optional<String> newName,
                            @RequestParam("newPassword") Optional<String> newPassword,
                            @RequestParam("newPhone") Optional<String> newPhone
                            ) throws UserParameterNotFoundException, UserAlreadyExistsException {
        Client admin = userService.findByUsername(usernameFrom);
        Client user = userService.findByUsername(usernameFor);
        user.setCredits(newCredits.orElse(user.getCredits()));
        user.setAdmin(newAdmin.orElse(user.isAdmin()));
        user.setName(newName.orElse(user.getName()));
        user.setPassword(newPassword.orElse(user.getPassword()));
        /*if (admin.isAdmin()) {
            user.setAdmin(false);
        }*/
        user.setPhone(newPhone.orElse(user.getPhone()));

        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
