package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NormalSubscription")
public class NormalSubscription {
    @Id
    @GeneratedValue
    private Long id;

    private int durationMonths;

}
