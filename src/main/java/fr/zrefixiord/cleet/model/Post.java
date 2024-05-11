package fr.zrefixiord.cleet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_gen")
    @SequenceGenerator(name = "posts_id_gen", sequenceName = "post_id_seq", allocationSize = 1)
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "content", nullable = false, length = 300)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "isreply", nullable = false)
    private Boolean isreply = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyto")
    private Post replyto;

    @ColumnDefault("0")
    @Column(name = "likes", nullable = false)
    private Integer likes=0;

    @ColumnDefault("0")
    @Column(name = "reposts", nullable = false)
    private Integer reposts=0;

    @ColumnDefault("0")
    @Column(name = "replies", nullable = false)
    private Integer replies=0;

}