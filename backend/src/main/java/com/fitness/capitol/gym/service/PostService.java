package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface PostService {
    List<Post> findAllByUser(User user);
    void save(String username, String text, String title);
}
