package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "Workout")
public class Workout implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    @JoinColumn
    @ManyToOne
    private User user;

    /*public Workout(){
        this.date = LocalDateTime.now();
    }

    public Workout(LocalDateTime date, User user) {
        this.date = date;
        this.user = user;
    }*/

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return Objects.equals(id, workout.id) &&
                Objects.equals(date, workout.date) &&
                Objects.equals(user, workout.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, user);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
