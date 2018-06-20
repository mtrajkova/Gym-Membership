package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Client_Post;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_PostRepository extends JpaRepository<Client_Post,Long> {
    List<Post> findAllByClient(Client client);
    List<Client> findAllByPost(Post post);
}
