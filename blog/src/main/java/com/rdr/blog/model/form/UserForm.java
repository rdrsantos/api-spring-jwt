package com.rdr.blog.model.form;

import com.rdr.blog.model.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class UserForm {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User toModel(UserForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setCreated_at(LocalDateTime.now());
        return user;
    }
}
