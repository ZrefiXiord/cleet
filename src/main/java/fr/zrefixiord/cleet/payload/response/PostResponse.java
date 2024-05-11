package fr.zrefixiord.cleet.payload.response;

import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@Getter
public class PostResponse {
    private String content;
    private String author;
    private String authorName;
    private Instant date;
    private Integer likes;
    private Integer replies;
    private Integer reposts;


    public PostResponse(String content, String author, String authorName, Instant date, Integer likes, Integer replies, Integer reposts) {
        this.content = content;
        this.author = author;
        this.authorName = authorName;
        this.date = date;
        this.likes = likes;
        this.replies = replies;
        this.reposts = reposts;
    }
}
