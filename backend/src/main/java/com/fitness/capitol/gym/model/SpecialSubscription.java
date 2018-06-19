package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SpecialSubscription")
public class SpecialSubscription extends Subscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date startOfRegistration;
    private Date endOfRegistration;
    private int durationMonths;


    public SpecialSubscription() {
    }

    public SpecialSubscription(Date startOfRegistration, Date endOfRegistration, int durationMonths, String name, Long price) {
        super(name, price);

        this.durationMonths = durationMonths;
        this.endOfRegistration = endOfRegistration;
        this.startOfRegistration = startOfRegistration;

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        super.setName(name);
        super.setPrice(price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialSubscription)) return false;
        if (!super.equals(o)) return false;
        SpecialSubscription that = (SpecialSubscription) o;
        return getDurationMonths() == that.getDurationMonths() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getStartOfRegistration(), that.getStartOfRegistration()) &&
                Objects.equals(getEndOfRegistration(), that.getEndOfRegistration());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId(), getStartOfRegistration(), getEndOfRegistration(), getDurationMonths());
    }
}
