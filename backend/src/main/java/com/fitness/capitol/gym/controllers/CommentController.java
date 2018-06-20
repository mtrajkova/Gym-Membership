package com.fitness.capitol.gym.controllers;

import com.fitness.capitol.gym.model.Comment;
import com.fitness.capitol.gym.model.Post;
import com.fitness.capitol.gym.model.Client;
import com.fitness.capitol.gym.service.CommentService;
import com.fitness.capitol.gym.service.PostService;
import com.fitness.capitol.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{post}", method = RequestMethod.GET)
    public List<Comment> getCommentsForPost(@PathVariable("post") String title) {
        Post post = postService.findByTitle(title);
        return commentService.findAllByPost(post);
    }

    @RequestMapping(value = "/{username}/{post}", method = RequestMethod.GET)
    public List<Comment> getCommentsForUserPerPost(@PathVariable("username") String username, @PathVariable("post") String title) {
        Post post = postService.findByTitle(title);
        Client client = userService.findByUsername(username);
        return commentService.findByClientAndPost(client, post);
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public void addComment(@RequestParam("text") String text, @RequestParam("postTitle") String title, @RequestParam("username") String username) {
        Client client = userService.findByUsername(username);
        Post post = postService.findByTitle(title);
        Date date = new Date();
        Comment comment = new Comment();
        comment.setDownvotes(0);
        comment.setPost(post);
        comment.setText(text);
        comment.setTimestamp(date);
        comment.setClient(client);
        comment.setUpvotes(0);
        commentService.save(comment);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public void voteComment(@RequestParam("text") String commentText, @RequestParam("title") String postTitle, @RequestParam("vote") String voteType) {
        Comment comment = commentService.findByText(commentText);
        Post post = postService.findByTitle(postTitle);
        if (voteType.equals("upvote")) {
            comment.upvoteComment();
        } else
            comment.downvoteComment();
        commentService.save(comment);
    }
}
