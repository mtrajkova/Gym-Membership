package com.fitness.capitol.gym.excpetions;

public class InvalidLoginCredentialsException extends Exception {
    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
