package com.fitness.capitol.gym.service;

import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.model.Post;

import java.util.List;

public interface User_PostService {
    List<Post> findAllByClient(Client client);
    List<Client> findAllByPost(Post post);
}
