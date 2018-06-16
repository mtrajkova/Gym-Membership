package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkoutSubscription")
public class WorkoutSubscription {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfDays;

}