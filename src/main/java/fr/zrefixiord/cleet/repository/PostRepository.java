package fr.zrefixiord.cleet.repository;

import fr.zrefixiord.cleet.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}