package com.vitordev.todolist.domain.post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Document(collection = "posts")
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String userId;
    private String title;
    private List<TodoPost> todos = new ArrayList<>();
    private List<PostIt> postIts = new ArrayList<>();

    public Post() {}

    public Post(String postId, String userId, String title) {
        this.id = postId;
        this.userId = userId;
        this.title = title;;
    }

    public List<TodoPost> getTodoPosts() {
        return todos;
    }

    public List<PostIt> getPostIts() {
        return postIts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", todos=" + todos +
                ", postIts=" + postIts +
                '}';
    }
}