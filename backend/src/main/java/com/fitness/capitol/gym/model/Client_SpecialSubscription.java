package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Client_SpecialSubscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime datePaused;
    private LocalDateTime dateStarted;

    @JoinColumn
    @ManyToOne
    private Client client;

    @JoinColumn
    @ManyToOne
    private SpecialSubscription specialSubscription;

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

    public SpecialSubscription getSpecialSubscription() {
        return specialSubscription;
    }

    public void setSpecialSubscription(SpecialSubscription specialSubscription) {
        this.specialSubscription = specialSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client_SpecialSubscription)) return false;
        Client_SpecialSubscription that = (Client_SpecialSubscription) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDatePaused(), that.getDatePaused()) &&
                Objects.equals(getDateStarted(), that.getDateStarted()) &&
                Objects.equals(getClient(), that.getClient()) &&
                Objects.equals(getSpecialSubscription(), that.getSpecialSubscription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDatePaused(), getDateStarted(), getClient(), getSpecialSubscription());
    }
}
