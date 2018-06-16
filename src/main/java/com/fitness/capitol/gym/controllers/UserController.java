package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public void save(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("isAdmin") boolean isAdmin){
        userService.save(new User(name,phone,isAdmin));
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return user;
    }


}
