package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Post post;

    private int downvotes;
    private int upvotes;
    private Date timestamp;
    private String text;
}
