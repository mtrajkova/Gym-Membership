package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;

import java.util.List;

public interface PostService {
    List<Post> findAllByCilent(Client client);
    void save(String username, String text, String title);
    Post findByTitle(String title);
}
