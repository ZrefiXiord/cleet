package fr.zrefixiord.cleet.controller;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import fr.zrefixiord.cleet.model.User;
import fr.zrefixiord.cleet.payload.request.LoginRequest;
import fr.zrefixiord.cleet.payload.request.RegisterRequest;
import fr.zrefixiord.cleet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private String createJwtToken(User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(999999999))
                .subject(user.getUsername())
                .claim("role", "simple")
                .build();
        NimbusJwtEncoder encoder = new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecretKey.getBytes()));
        JwtEncoderParameters params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setDisplayName(request.getDisplayName());
        user.setCreation(Instant.now());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        String token = createJwtToken(user);
        if(userService.saveUser(user) == null)
            return ResponseEntity.badRequest().body("Username or email already in use");

        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        User user= userService.findUserByUsername(request.getLogin());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        if (passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            String token = createJwtToken(user);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}
