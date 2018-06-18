package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class User_NormalSubscription implements Serializable {
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
    private NormalSubscription normalSubscription;

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

    public NormalSubscription getNormalSubscription() {
        return normalSubscription;
    }

    public void setNormalSubscription(NormalSubscription normalSubscription) {
        this.normalSubscription = normalSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User_NormalSubscription)) return false;
        User_NormalSubscription that = (User_NormalSubscription) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDatePaused(), that.getDatePaused()) &&
                Objects.equals(getDateStarted(), that.getDateStarted()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getNormalSubscription(), that.getNormalSubscription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDatePaused(), getDateStarted(), getUser(), getNormalSubscription());
    }
}
