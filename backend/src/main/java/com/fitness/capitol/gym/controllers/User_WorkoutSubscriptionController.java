package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Client_WorkoutSubscription;
import com.fitness.capitol.gym.model.WorkoutSubscription;
import com.fitness.capitol.gym.service.UserService;
import com.fitness.capitol.gym.service.User_WorkoutSubscriptionService;
import com.fitness.capitol.gym.service.WorkoutSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/myWorkoutSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class User_WorkoutSubscriptionController {
    @Autowired
    private User_WorkoutSubscriptionService user_workoutSubscriptionService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutSubscriptionService workoutSubscriptionService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public List<WorkoutSubscription> getMyWorkoutSubs(@PathVariable("username") String username) {
        Client client = userService.findByUsername(username);
        return user_workoutSubscriptionService.getAllWorkoutSubsByClient(client);
    }

    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    public ResponseEntity addWorkoutSubscription(
                                                 @RequestParam("name") String name,
                                                 @RequestParam("price") Long price,
                                                 @RequestParam("username") String username) {

        WorkoutSubscription workoutSubscription = workoutSubscriptionService.findByName(name);
        Client client = userService.findByUsername(username);

        if (workoutSubscription == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This workout subscription doesn't exist");
        } else if (user_workoutSubscriptionService.findByClientAndWorkoutSubscription(client, workoutSubscription) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have already subscribed to this subscription!");
        } else if (client.getCredits() < price) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You don't have enough credits!");
        } else {
            user_workoutSubscriptionService.save(workoutSubscription, client);
            client.setCredits((int) (client.getCredits() - price));
            return ResponseEntity.status(HttpStatus.OK).body("Subscribed!");
        }
        /*workoutSubscription.setNumberOfDays(days);
        workoutSubscription.setSuper(name, price);
        workoutSubscriptionService.save(workoutSubscription);*/


    }

    @RequestMapping(value = "/useOneWorkout", method = RequestMethod.POST)
    public ResponseEntity useOneWorkout(@RequestParam("username") String username, @RequestParam("workoutSubName") String workoutSubName) {
        Client client = userService.findByUsername(username);
        WorkoutSubscription workoutSubscription = workoutSubscriptionService.findByName(workoutSubName);
        Client_WorkoutSubscription client_workoutSubscription = user_workoutSubscriptionService.findByClientAndWorkoutSubscription(client, workoutSubscription);
        if (client_workoutSubscription == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client isn't subscribed to this subscription!");
        } else if (client_workoutSubscription.getWorkoutsLeft() <= 0) {
            user_workoutSubscriptionService.deleteClient_WorkoutSubscriptionByClientAndWorkoutSubscription(client, workoutSubscription);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client doesn't have enough workouts");
        } else {
            client_workoutSubscription.setWorkoutsLeft(client_workoutSubscription.getWorkoutsLeft() - 1);
            return ResponseEntity.status(HttpStatus.OK).body("Client's number of workouts left has been saved!");
        }

    }
}
