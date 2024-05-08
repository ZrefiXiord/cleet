package fr.zrefixiord.cleet.repository;

import fr.zrefixiord.cleet.model.Repost;
import fr.zrefixiord.cleet.model.RepostId;
import org.springframework.data.repository.CrudRepository;

public interface RepostRepository extends CrudRepository<Repost, RepostId> {
}