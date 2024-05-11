package fr.zrefixiord.cleet.controller;

import fr.zrefixiord.cleet.model.Post;
import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.payload.response.PostResponse;
import fr.zrefixiord.cleet.service.PostService;
import fr.zrefixiord.cleet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

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

    @GetMapping("/user/{username}")
    public ResponseEntity<Object> getUserPosts(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        ArrayList<PostResponse> posts = new ArrayList<>();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        postService.getPostsByUser(user).forEach(post -> posts.add(new PostResponse(post.getContent(),user.getUsername(),user.getDisplayName(), post.getCreatedAt(), post.getLikes(), post.getReplies(), post.getReposts())));
        return ResponseEntity.ok().body(posts);
    }
}
