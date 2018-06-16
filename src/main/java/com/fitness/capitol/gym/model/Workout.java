package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Workout")
public class Workout {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @JoinColumn
    @ManyToOne
    private User user;

}
