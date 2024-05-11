package fr.zrefixiord.cleet.service;

import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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
        return userRepository.findByEmail(email).orElse(null);
    }
    public User findUserByUsername(final String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
