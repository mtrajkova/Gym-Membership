package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.persistance.PostRepository;
import com.fitness.capitol.gym.persistance.UserRepository;
import com.fitness.capitol.gym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> findAllByClient(Client client) {
        return postRepository.findAllByClient(client);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    @Override
    public List<Post> getAllByAdmin() {
        List<Post> posts = new ArrayList<>();
        for (Post p : postRepository.findAll()) {
            if (p.getClient().isAdmin()) {
                posts.add(p);
            }
        }
        return posts;
    }
}
