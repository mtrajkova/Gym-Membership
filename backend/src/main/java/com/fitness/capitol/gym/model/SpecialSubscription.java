package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SpecialSubscription")
public class SpecialSubscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date startOfRegistration;
    private Date endOfRegistration;
    private int durationMonths;

    private String name;
    private Long price;


    public SpecialSubscription() {
    }

    public SpecialSubscription(Date startOfRegistration, Date endOfRegistration, int durationMonths,String name, Long price){
        this.startOfRegistration=startOfRegistration;
        this.endOfRegistration=endOfRegistration;
        this.durationMonths=durationMonths;
        this.price=price;
        this.name=name;
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

    public Date getStartOfRegistration() {
        return startOfRegistration;
    }

    public void setStartOfRegistration(Date startOfRegistration) {
        this.startOfRegistration = startOfRegistration;
    }

    public Date getEndOfRegistration() {
        return endOfRegistration;
    }

    public void setEndOfRegistration(Date endOfRegistration) {
        this.endOfRegistration = endOfRegistration;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public void setSuper(String name, Long price){
        this.setName(name);
        this.setPrice(price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialSubscription)) return false;
        SpecialSubscription that = (SpecialSubscription) o;
        return getDurationMonths() == that.getDurationMonths() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getStartOfRegistration(), that.getStartOfRegistration()) &&
                Objects.equals(getEndOfRegistration(), that.getEndOfRegistration()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStartOfRegistration(), getEndOfRegistration(), getDurationMonths(), getName(), getPrice());
    }
}
