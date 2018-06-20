package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.persistance.CommentRepository;
import com.fitness.capitol.gym.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAllByClient(Client client) {
        return commentRepository.findAllByClient(client);
    }

    @Override
    public List<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }

    @Override
    public List<Comment> findByClientAndPost(Client client, Post post) {
        return commentRepository.findByClientAndPost(client, post);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findByText(String text) {
        return commentRepository.findByText(text);
    }
}
