package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class User_WorkoutSubscription implements Serializable {
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
    private WorkoutSubscription workoutSubscription;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(o instanceof User_WorkoutSubscription)) return false;
        User_WorkoutSubscription that = (User_WorkoutSubscription) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDatePaused(), that.getDatePaused()) &&
                Objects.equals(getDateStarted(), that.getDateStarted()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getWorkoutSubscription(), that.getWorkoutSubscription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDatePaused(), getDateStarted(), getUser(), getWorkoutSubscription());
    }
}
