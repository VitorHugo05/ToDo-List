package com.vitordev.todolist.domain.post;

import com.vitordev.todolist.domain.post.enums.TodoStatus;


import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Todo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;;

    private String id;
    private String content;
    private TodoStatus status;

    public Todo() {}

    public Todo(TodoStatus status, String content) {
        this.status = status;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
