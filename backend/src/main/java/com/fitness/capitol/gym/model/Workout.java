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

    private LocalDateTime date;
    private String workoutName;

    @JoinColumn
    @ManyToOne
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return Objects.equals(getId(), workout.getId()) &&
                Objects.equals(getDate(), workout.getDate()) &&
                Objects.equals(getWorkoutName(), workout.getWorkoutName()) &&
                Objects.equals(getClient(), workout.getClient());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getWorkoutName(), getClient());
    }
}
