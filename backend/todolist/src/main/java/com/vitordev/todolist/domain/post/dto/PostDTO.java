package com.vitordev.todolist.domain.post.dto;

import com.vitordev.todolist.domain.post.Post;

public class PostDTO {

    private String id;
    private String userId;
    private String title;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostDTO() {}

    public PostDTO(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
    }
}
