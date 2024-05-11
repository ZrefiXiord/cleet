package fr.zrefixiord.cleet.service;

import fr.zrefixiord.cleet.model.Post;
import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }
    public List<Post> getPostsByUser(User user) {
        List<Post> posts = new ArrayList<>();
        postRepository.findAllByUser(user).forEach(posts::add);
        return posts;
    }
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

}
