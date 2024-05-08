package fr.zrefixiord.cleet.repository;

import fr.zrefixiord.cleet.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}