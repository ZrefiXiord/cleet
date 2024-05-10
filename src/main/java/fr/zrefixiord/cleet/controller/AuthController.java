package fr.zrefixiord.cleet.controller;

import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.payload.request.LoginRequest;
import fr.zrefixiord.cleet.payload.request.RegisterRequest;
import fr.zrefixiord.cleet.repository.UserRepository;
import fr.zrefixiord.cleet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setDisplayName(request.getDisplayName());
        user.setCreation(Instant.now());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        if(userService.saveUser(user) == null)
            return ResponseEntity.badRequest().body("Username or email already in use");

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        User user= userService.findUserByUsername(request.getLogin());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        return ResponseEntity.ok("Login successfully");


    }
}
