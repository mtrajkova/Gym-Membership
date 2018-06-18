package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "WorkoutSubscription")
public class WorkoutSubscription extends Subscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfDays;

    public WorkoutSubscription() {
    }

    public WorkoutSubscription(int numberOfDays, String name, Long price) {
        super(name, price);
        this.numberOfDays = numberOfDays;

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setSuper(String name, Long price){
        super.setName(name);
        super.setPrice(price);
    }
    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkoutSubscription)) return false;
        if (!super.equals(o)) return false;
        WorkoutSubscription that = (WorkoutSubscription) o;
        return getNumberOfDays() == that.getNumberOfDays() &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId(), getNumberOfDays());
    }
}