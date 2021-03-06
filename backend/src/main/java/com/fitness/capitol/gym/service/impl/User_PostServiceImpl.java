package com.fitness.capitol.gym.service.impl;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.persistance.User_PostRepository;
import com.fitness.capitol.gym.service.User_PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_PostServiceImpl implements User_PostService {
    @Autowired
    private User_PostRepository user_postRepository;

    @Override
    public List<Post> findAllByClient(Client client) {
        return user_postRepository.findAllByClient(client);
    }

    @Override
    public List<Client> findAllByPost(Post post) {
        return user_postRepository.findAllByPost(post);
    }
}
