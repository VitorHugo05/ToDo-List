package com.vitordev.todolist.domain.post;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
@Document(collection = "posts")
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String userId;
    private String title;
    private List<Todo> todos = new ArrayList<>();
}