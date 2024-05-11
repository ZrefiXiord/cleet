package fr.zrefixiord.cleet.repository;

import fr.zrefixiord.cleet.model.Post;
import fr.zrefixiord.cleet.model.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findAllByUser(User user);
}