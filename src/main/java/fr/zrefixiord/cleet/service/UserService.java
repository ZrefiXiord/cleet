package fr.zrefixiord.cleet.service;

import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Integer id){
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(final Integer id) {
        userRepository.deleteById(id);
    }

    public User saveUser(final User user) {
        if(findUserByEmail(user.getEmail()) != null)
            return null;
        if(findUserByUsername(user.getUsername()) != null)
            return null;
        return userRepository.save(user);
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }
    public User findUserByUsername(final String username) {
        return userRepository.findByUsername(username);
    }
}
