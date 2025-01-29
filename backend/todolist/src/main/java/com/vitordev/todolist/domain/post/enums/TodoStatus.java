package com.vitordev.todolist.domain.post.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum TodoStatus{
    TO_DO("To Do"),
    DONE("Done");

    private String status;

    TodoStatus(String role){
        this.status = role;
    }

    @JsonValue
    public String getStatus(){
        return status;
    }

    @JsonCreator
    public static TodoStatus fromString(String status) {
        return Stream.of(TodoStatus.values())
                .filter(c -> c.status.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + status));
    }
}
