package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;

    @Column(unique = true)
    private String username;

    private String password;
    private int credits;
    private boolean isAdmin;
    private LocalDateTime dateJoined;

    public Client() {
    }

    public Client(String name, String phone, boolean isAdmin) {
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
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getCredits() == client.getCredits() &&
                isAdmin() == client.isAdmin() &&
                Objects.equals(getId(), client.getId()) &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getPhone(), client.getPhone()) &&
                Objects.equals(getUsername(), client.getUsername()) &&
                Objects.equals(getPassword(), client.getPassword()) &&
                Objects.equals(getDateJoined(), client.getDateJoined());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPhone(), getUsername(), getPassword(), getCredits(), isAdmin(), getDateJoined());
    }
}
