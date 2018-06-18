package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;

import java.util.List;

public interface User_PostService {
    List<Post> findAllByUser(User user);
    List<User> findAllByPost(Post post);
}
