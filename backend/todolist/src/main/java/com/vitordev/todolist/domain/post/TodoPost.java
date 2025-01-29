package com.vitordev.todolist.domain.post;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class TodoPost {

    @Id
    private String id;
    private String title;
    private List<Todo> todos = new ArrayList<>();

    public TodoPost() {}

    public TodoPost(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
