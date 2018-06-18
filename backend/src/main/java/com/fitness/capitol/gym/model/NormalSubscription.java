package com.fitness.capitol.gym.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "NormalSubscription")
public class NormalSubscription extends Subscription implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int durationMonths;

    public NormalSubscription() {
    }

    public NormalSubscription(int durationMonths, String name, Long price) {
        super(name, price);
        this.durationMonths = durationMonths;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!super.equals(o)) return false;
        NormalSubscription that = (NormalSubscription) o;
        return getDurationMonths() == that.getDurationMonths() &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId(), getDurationMonths());
    }
}
