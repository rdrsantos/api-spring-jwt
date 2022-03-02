package com.rdr.blog.model.form;

import com.rdr.blog.model.Post;
import com.rdr.blog.model.User;
import java.time.LocalDateTime;

public class PostForm {
    private String title;
    private String body;

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

    public static Post toModel(PostForm form, User user) {
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        post.setCreated_at(LocalDateTime.now());
        post.setUser(user);
        return post;
    }
}
