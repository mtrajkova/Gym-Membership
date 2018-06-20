package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByClient(Client client);

    List<Comment> findAllByPost(Post post);

    List<Comment> findByClientAndPost(Client client, Post post);

    Comment findByText(String text);
}
