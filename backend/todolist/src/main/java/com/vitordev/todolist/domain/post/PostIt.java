package com.vitordev.todolist.domain.post;

import com.vitordev.todolist.domain.post.enums.PostItColor;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


public class PostIt implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String content;

    private PostItColor color;

    public PostIt() {}

    public PostIt(PostItColor color, String content) {
        this.color = color;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public PostItColor getColor() {
        return color;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setColor(PostItColor color) {
        this.color = color;
    }
}
