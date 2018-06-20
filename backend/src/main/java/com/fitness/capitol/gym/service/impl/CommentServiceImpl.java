package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;
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
    public List<Comment> findAllBYUser(User user) {
        return commentRepository.findAllByUser(user);
    }

    @Override
    public List<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }

    @Override
    public List<Comment> findByUserAndPost(User user, Post post) {
        return commentRepository.findByUserAndPost(user, post);
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
