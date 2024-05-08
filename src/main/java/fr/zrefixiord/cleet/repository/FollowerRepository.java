package fr.zrefixiord.cleet.repository;

import fr.zrefixiord.cleet.model.Follower;
import fr.zrefixiord.cleet.model.FollowerId;
import org.springframework.data.repository.CrudRepository;

public interface FollowerRepository extends CrudRepository<Follower, FollowerId> {
}