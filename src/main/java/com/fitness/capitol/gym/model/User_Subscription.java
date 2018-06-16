package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "User_Subscription")
public class User_Subscription {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime datePaused;
    private LocalDateTime dateStarted;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Subscription subscription;
}
