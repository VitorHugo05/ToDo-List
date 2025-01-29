package com.vitordev.todolist.domain.post.dto;


public class PostItUpdateDTO {
    private String content;
    private String color;

    public PostItUpdateDTO() {}

    public PostItUpdateDTO(String content, String color) {
        this.content = content;
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
