package fr.zrefixiord.cleet.controller;

import fr.zrefixiord.cleet.model.Post;
import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.service.PostService;
import fr.zrefixiord.cleet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/post/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PutMapping("/create")
    public ResponseEntity<Object> createPost(Authentication authentication, @RequestBody String content) {
        User user = userService.findUserByUsername(authentication.getName());
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        post.setCreatedAt(Instant.now());
        post.setIsreply(false);
        postService.savePost(post);
        return ResponseEntity.ok("post created");
    }
}
