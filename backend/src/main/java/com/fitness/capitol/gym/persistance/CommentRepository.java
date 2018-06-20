package com.fitness.capitol.gym.persistance;

import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);

    List<Comment> findAllByPost(Post post);

    List<Comment> findByUserAndPost(User user, Post post);

    Comment findByText(String text);
}
