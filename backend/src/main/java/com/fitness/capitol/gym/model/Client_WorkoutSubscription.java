package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Client_WorkoutSubscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime datePaused;
    private LocalDateTime dateStarted;

    private int workoutsLeft;

    @JoinColumn
    @ManyToOne
    private Client client;

    @JoinColumn
    @ManyToOne
    private WorkoutSubscription workoutSubscription;

    public int getWorkoutsLeft() {
        return workoutsLeft;
    }

    public void setWorkoutsLeft(int workoutsLeft) {
        this.workoutsLeft = workoutsLeft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatePaused() {
        return datePaused;
    }

    public void setDatePaused(LocalDateTime datePaused) {
        this.datePaused = datePaused;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public WorkoutSubscription getWorkoutSubscription() {
        return workoutSubscription;
    }

    public void setWorkoutSubscription(WorkoutSubscription workoutSubscription) {
        this.workoutSubscription = workoutSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client_WorkoutSubscription)) return false;
        Client_WorkoutSubscription that = (Client_WorkoutSubscription) o;
        return getWorkoutsLeft() == that.getWorkoutsLeft() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDatePaused(), that.getDatePaused()) &&
                Objects.equals(getDateStarted(), that.getDateStarted()) &&
                Objects.equals(getClient(), that.getClient()) &&
                Objects.equals(getWorkoutSubscription(), that.getWorkoutSubscription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDatePaused(), getDateStarted(), getWorkoutsLeft(), getClient(), getWorkoutSubscription());
    }
}
