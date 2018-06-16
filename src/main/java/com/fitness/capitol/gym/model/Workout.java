package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Workout")
public class Workout {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    @JoinColumn
    @ManyToOne
    private User user;

    public Workout(LocalDateTime date, User user) {
        this.date = date;
        this.user = user;
    }
}
