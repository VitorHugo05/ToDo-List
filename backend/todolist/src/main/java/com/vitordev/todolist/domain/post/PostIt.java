package com.vitordev.todolist.domain.post;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;

public class PostIt implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String content;
    private String color;
}
