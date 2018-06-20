package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Post")
public class Post implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Client")
    private Client client;

    private String text;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) &&
                Objects.equals(getClient(), post.getClient()) &&
                Objects.equals(getText(), post.getText()) &&
                Objects.equals(getTitle(), post.getTitle());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getClient(), getText(), getTitle());
    }
}
