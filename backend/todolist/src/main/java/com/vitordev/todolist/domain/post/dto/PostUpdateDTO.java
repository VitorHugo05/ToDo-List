package com.vitordev.todolist.domain.post.dto;

public class PostUpdateDTO {
    private String title;

    public PostUpdateDTO() {}

    public PostUpdateDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
