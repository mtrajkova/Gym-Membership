package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SpecialSubscription")
public class SpecialSubscription {
    @Id
    @GeneratedValue
    private Long id;

    private Date startOfRegistration;
    private Date endOfRegistration;
    private int durationMonths;

    @JoinColumn
    @OneToOne
    private Subscription subscription;
}
