package fr.zrefixiord.cleet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FollowerId implements java.io.Serializable {
    private static final long serialVersionUID = 5879562214935566210L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "follower_id", nullable = false)
    private Integer followerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FollowerId entity = (FollowerId) o;
        return Objects.equals(this.followerId, entity.followerId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, userId);
    }

}