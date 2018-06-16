package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long price;
}
