package com.rdr.blog.model.dto;

import com.rdr.blog.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public UserDto(){}

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static UserDto toDto(User admin){
        return new UserDto(admin);
    }

    public static List<UserDto> toDtos(List<User> admins) {
        return admins.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
