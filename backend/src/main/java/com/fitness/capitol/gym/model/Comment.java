package com.fitness.capitol.gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Post post;

    private int downvotes;
    private int upvotes;
    private Date timestamp;
    private String text;

    public void upvoteComment() {
        upvotes++;
    }

    public void downvoteComment() {
        downvotes++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getDownvotes() == comment.getDownvotes() &&
                getUpvotes() == comment.getUpvotes() &&
                Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getUser(), comment.getUser()) &&
                Objects.equals(getPost(), comment.getPost()) &&
                Objects.equals(getTimestamp(), comment.getTimestamp()) &&
                Objects.equals(getText(), comment.getText());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUser(), getPost(), getDownvotes(), getUpvotes(), getTimestamp(), getText());
    }
}
