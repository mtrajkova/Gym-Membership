package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.persistance.UserRepository;
import com.fitness.capitol.gym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/myPosts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public List<Post> getAllPosts(){
        return postService.findAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public List<Post> getMyPosts(@PathVariable("username") String username) {
        return postService.findAllByClient((Client) userRepository.findByUsername(username));
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public void addPost(@RequestParam("username") String username,
                        @RequestParam("text") String text,
                        @RequestParam("title") String title) {
        Post post = new Post();
        post.setClient(userRepository.findByUsername(username));
        post.setTitle(title);
        post.setText(text);
        postService.save(post);
    }

    @RequestMapping(value = "/byAdmin", method = RequestMethod.GET)
    public List<Post> getPostsByAdmin() {
        return postService.getAllByAdmin();

    }
}
