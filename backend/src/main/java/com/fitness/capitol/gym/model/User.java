package com.fitness.capitol.gym.model;

import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String phone;

    @Column(unique = true)
    private String username;

    private String password;
    private int credits;
    private boolean isAdmin;
    private LocalDateTime dateJoined;

    public User() {
    }

    public User(String name, String phone, boolean isAdmin) {
        this.name = name;
        this.phone = phone;
        this.credits = 0;
        this.isAdmin = isAdmin;
        LocalDateTime date = LocalDateTime.now();
        this.dateJoined = date;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getCredits() == user.getCredits() &&
                isAdmin() == user.isAdmin() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getDateJoined(), user.getDateJoined());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPhone(), getUsername(), getPassword(), getCredits(), isAdmin(), getDateJoined());
    }
}
