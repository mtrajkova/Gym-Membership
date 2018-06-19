package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Workout")
public class Workout implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date date;
    private String workoutName;

    @JoinColumn
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return Objects.equals(getId(), workout.getId()) &&
                Objects.equals(getDate(), workout.getDate()) &&
                Objects.equals(getWorkoutName(), workout.getWorkoutName()) &&
                Objects.equals(getUser(), workout.getUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getWorkoutName(), getUser());
    }
}
