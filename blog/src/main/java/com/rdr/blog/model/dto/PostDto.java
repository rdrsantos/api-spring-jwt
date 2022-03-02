package com.rdr.blog.model.dto;

import com.rdr.blog.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.created_at = post.getCreated_at();
        this.updated_at = post.getUpdated_at();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public static List<PostDto> toDtos(List<Post> posts) {
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }
}
