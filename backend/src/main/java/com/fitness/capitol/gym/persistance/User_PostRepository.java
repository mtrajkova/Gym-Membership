package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.model.User_Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_PostRepository extends JpaRepository<User_Post,Long> {
    List<Post> findAllByUser(User user);
    List<User> findAllByPost(Post post);
}
