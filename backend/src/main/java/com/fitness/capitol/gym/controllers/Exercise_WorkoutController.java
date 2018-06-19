package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.excpetions.ExerciseAlreadyExistsException;
import com.fitness.capitol.gym.model.Exercise;
import com.fitness.capitol.gym.model.Workout;
import com.fitness.capitol.gym.model.Workout_Exercise;
import com.fitness.capitol.gym.service.ExerciseService;
import com.fitness.capitol.gym.service.WorkoutService;
import com.fitness.capitol.gym.service.Workout_ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/exercises", produces = MediaType.APPLICATION_JSON_VALUE)
public class Exercise_WorkoutController {

    @Autowired
    private Workout_ExerciseService workout_exerciseService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private WorkoutService workoutService;

    //    PROBAJ GOOOO OVA **************
    @RequestMapping(method = RequestMethod.GET)
    public List<Exercise> getAllExercisesByWorkout(@RequestParam("workoutId") Long workoutId) {
        return workout_exerciseService.findAllByWorkoutId(workoutId);
    }

    @RequestMapping(value = "/addExercise", method = RequestMethod.POST)
    public ResponseEntity addExercise(@RequestParam("workoutId") Long workoutId,
                                      @RequestParam("name") String name) throws ExerciseAlreadyExistsException {

        Exercise exercise;
        try {
            exercise = exerciseService.findByName(name);

            if (exercise == null) {
                exercise = new Exercise();
                exercise.setName(name);
                exerciseService.save(exercise);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        Workout workout = workoutService.findById(workoutId);
        Workout_Exercise workout_exercise = new Workout_Exercise();
        workout_exercise.setExercise(exercise);
        workout_exercise.setWorkout(workout);
        workout_exerciseService.saveExerciseForWorkout(workout_exercise);
        return ResponseEntity.status(HttpStatus.OK).body("Exercise saved");

    }

}
