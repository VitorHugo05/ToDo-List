package com.vitordev.todolist.domain.user.dto;

import com.vitordev.todolist.domain.user.enums.UserRole;
import lombok.*;

public class RegisterDTO {
    private String email;
    private String username;
    private String password;
    private UserRole role;

    public RegisterDTO() {}

    public RegisterDTO(String email, String username, String password, UserRole role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
