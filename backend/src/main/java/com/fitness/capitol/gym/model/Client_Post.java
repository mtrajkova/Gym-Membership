package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Client_Post")
public class Client_Post implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private Client client;

    @JoinColumn
    @ManyToOne
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client_Post)) return false;
        Client_Post client_post = (Client_Post) o;
        return Objects.equals(getId(), client_post.getId()) &&
                Objects.equals(getClient(), client_post.getClient()) &&
                Objects.equals(getPost(), client_post.getPost());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getClient(), getPost());
    }
}
