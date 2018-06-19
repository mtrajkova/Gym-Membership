package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
    Post findByTitle(String title);
}
