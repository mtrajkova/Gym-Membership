package com.fitness.capitol.gym.model;

import javax.persistence.*;


@Entity
@Table(name = "sets")
public class Sets {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfSet;
    private Long weight;
    private int numberOfReps;

    @JoinColumn
    @ManyToOne
    private Exercise exercise;

}
