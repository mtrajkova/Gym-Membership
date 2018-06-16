package com.fitness.capitol.gym.model;

import javax.persistence.*;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User")
    private User user;

    private String text;
    private String title;

}
