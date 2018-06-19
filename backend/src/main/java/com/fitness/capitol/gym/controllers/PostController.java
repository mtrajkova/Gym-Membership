package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.User;
import com.fitness.capitol.gym.persistance.PostRepository;
import com.fitness.capitol.gym.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/myPosts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public List<Post> getMyPosts(@PathVariable("username") String username) {
        return postRepository.findAllByUser((User) userRepository.findByUsername(username));
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public void addPost(@RequestParam("username") String username,
                        @RequestParam("text") String text,
                        @RequestParam("title") String title) {
        Post post = new Post();
        post.setUser(userRepository.findByUsername(username));
        post.setTitle(title);
        post.setText(text);
        postRepository.save(post);
    }
}
