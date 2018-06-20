package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByClient(Client client);

    List<Comment> findAllByPost(Post post);

    List<Comment> findByClientAndPost(Client client, Post post);

    void save(Comment comment);

    Comment findByText(String text);

}
