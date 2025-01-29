package com.vitordev.todolist.domain.user.dto;

import com.vitordev.todolist.domain.post.Post;
import com.vitordev.todolist.domain.user.User;
import com.vitordev.todolist.domain.user.enums.UserRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userId;
    private String email;
    private String username;
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private List<String> posts = new ArrayList<>();

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public List<String> getPosts() {
        return posts;
    }

    public UserDTO(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.posts = user.getPosts().stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        this.role = user.getRole();
    }
}
