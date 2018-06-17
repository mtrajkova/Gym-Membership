package com.fitness.capitol.gym.excpetions;

import com.fitness.capitol.gym.model.Exercise;

public class ExerciseAlreadyExistsException extends Exception {
    public ExerciseAlreadyExistsException(String message) {
        super(message);
    }
}
