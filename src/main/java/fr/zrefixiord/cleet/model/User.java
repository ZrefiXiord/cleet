package fr.zrefixiord.cleet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Size(max = 32)
    @NotNull
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @NotNull
    @Column(name = "password_hash", nullable = false, length = Integer.MAX_VALUE)
    private String passwordHash;

    @Size(max = 200)
    @Column(name = "bio", length = 200)
    private String bio;

    @NotNull
    @Column(name = "creation", nullable = false)
    private Instant creation;

    @Size(max = 50)
    @NotNull
    @Column(name = "display_name", nullable = false, length = 50)
    private String displayName;

    @Column(name = "followers")
    private Integer followers;

    @Column(name = "following")
    private Integer following;

    @Column(name = "token")
    @JdbcTypeCode(SqlTypes.JSON)
    private ArrayList<String> token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return displayName;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}