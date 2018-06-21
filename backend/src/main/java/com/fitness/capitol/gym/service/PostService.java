package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findAllByClient(Client client);
    void save(Post post);
    Post findByTitle(String title);
    List<Post> getAllByAdmin();
}
