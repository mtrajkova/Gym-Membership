package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface CommentService {
    List<Comment> findAllBYUser(User user);

    List<Comment> findAllByPost(Post post);

    List<Comment> findByUserAndPost(User user, Post post);

    void save(Comment comment);

    Comment findByText(String text);

}
