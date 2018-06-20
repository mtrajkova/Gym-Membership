package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Client_Subscription")
public class Client_Subscription implements Serializable {
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
    private Subscription subscription;

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

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client_Subscription)) return false;
        Client_Subscription that = (Client_Subscription) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDatePaused(), that.getDatePaused()) &&
                Objects.equals(getDateStarted(), that.getDateStarted()) &&
                Objects.equals(getClient(), that.getClient()) &&
                Objects.equals(getSubscription(), that.getSubscription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDatePaused(), getDateStarted(), getClient(), getSubscription());
    }
}
