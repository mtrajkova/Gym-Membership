package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.persistance.PostRepository;
import com.fitness.capitol.gym.persistance.UserRepository;
import com.fitness.capitol.gym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> findAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    public void save(String username, String text, String title) {
        Post post = new Post();
        post.setText(text);
        post.setTitle(title);
        post.setUser((User)userRepository.findByUsername(username));
        postRepository.save(post);
    }
}
