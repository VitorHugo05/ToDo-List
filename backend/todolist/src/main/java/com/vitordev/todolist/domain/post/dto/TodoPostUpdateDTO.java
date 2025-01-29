package com.vitordev.todolist.domain.post.dto;

public class TodoPostUpdateDTO {
    private String title;

    public TodoPostUpdateDTO() {}

    public TodoPostUpdateDTO(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
