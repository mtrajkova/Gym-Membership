package com.fitness.capitol.gym.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "User")
public class User {
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
}
