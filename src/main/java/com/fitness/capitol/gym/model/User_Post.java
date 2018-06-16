package com.fitness.capitol.gym.model;

import javax.persistence.*;

@Entity
@Table(name = "User_Post")
public class User_Post {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Post post;
}
