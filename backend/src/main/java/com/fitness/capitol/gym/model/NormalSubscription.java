package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "NormalSubscription")
public class NormalSubscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int durationMonths;

    private String name;

    private Long price;


    public void setSuper(String name, Long price) {
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

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalSubscription)) return false;
        NormalSubscription that = (NormalSubscription) o;
        return getDurationMonths() == that.getDurationMonths() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDurationMonths(), getName(), getPrice());
    }
}
