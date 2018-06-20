package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "WorkoutSubscription")
public class WorkoutSubscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfDays;

    private String name;

    private Long price;

    public WorkoutSubscription() {
    }

    public WorkoutSubscription(int numberOfDays, String name, Long price) {
        setSuper(name,price);
        this.numberOfDays = numberOfDays;

    }



    public void setSuper(String name, Long price){
        this.setName(name);
        this.setPrice(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
        WorkoutSubscription that = (WorkoutSubscription) o;
        return getNumberOfDays() == that.getNumberOfDays() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getNumberOfDays(), getName(), getPrice());
    }
}