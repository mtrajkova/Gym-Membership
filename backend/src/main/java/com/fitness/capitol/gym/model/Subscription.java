package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Subscription")
public class Subscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;


    private Long price;

    public Subscription() {
    }

    public Subscription(String name, Long price) {
        this.name = name;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPrice());
    }
}
