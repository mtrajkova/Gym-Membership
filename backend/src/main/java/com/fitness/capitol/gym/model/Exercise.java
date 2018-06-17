package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Exercise")
public class Exercise {
    @Id
    private String name;

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(){}
}
