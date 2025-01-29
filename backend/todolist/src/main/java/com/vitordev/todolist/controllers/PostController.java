package com.vitordev.todolist.controllers;

import com.vitordev.todolist.domain.post.Post;
import com.vitordev.todolist.domain.post.PostIt;
import com.vitordev.todolist.domain.post.Todo;
import com.vitordev.todolist.domain.post.TodoPost;
import com.vitordev.todolist.domain.post.dto.PostItUpdateDTO;
import com.vitordev.todolist.domain.post.dto.PostUpdateDTO;
import com.vitordev.todolist.domain.post.dto.TodoPostUpdateDTO;
import com.vitordev.todolist.domain.post.enums.PostItColor;
import com.vitordev.todolist.domain.post.enums.TodoStatus;
import com.vitordev.todolist.domain.user.User;
import com.vitordev.todolist.domain.post.dto.PostDTO;
import com.vitordev.todolist.services.PostService;
import com.vitordev.todolist.services.UserService;
import com.vitordev.todolist.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // Post endpoints

    @GetMapping(value = "/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable String postId) {
        Post post = postService.findById(postId);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody PostDTO postDto) {
        Post post = new Post(postDto.getId(), postDto.getUserId(), postDto.getTitle());
        post = postService.save(post);
        User user = userService.findById(postDto.getUserId());
        user.getPosts().add(post);
        userService.save(user);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable String postId, @RequestBody PostUpdateDTO postUpdateDto) {
        Post post = postService.findById(postId);

        post.setTitle(postUpdateDto.getTitle());
        postService.save(post);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId) {
        Post post = postService.findById(postId);
        postService.delete(post);
        return ResponseEntity.noContent().build();
    }

    // Post-it endpoints

    @PostMapping(value = "/{postId}/postit")
    public ResponseEntity<Void> addPostIt(@PathVariable String postId, @RequestBody PostIt postIt) {
        Post post = postService.findById(postId);
        postIt.setId(UUID.randomUUID().toString());
        if(postIt.getColor() == null) {
            postIt.setColor(PostItColor.YELLOW);
        }
        post.getPostIts().add(postIt);
        postService.save(post);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(value = "/{postId}/postit/{postItId}")
    public ResponseEntity<Void> updatePostIt(@PathVariable String postId, @PathVariable String postItId,
                                             @RequestBody PostItUpdateDTO postItUpdateDto) {
        Post post = postService.findById(postId);

        PostIt matchPostIt = post.getPostIts().stream()
                .filter(postIt -> Objects.equals(postIt.getId(), postItId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("PostIt not found"));

        matchPostIt.setContent(postItUpdateDto.getContent());

        String colorString = postItUpdateDto.getColor();
        if (colorString != null) {
            matchPostIt.setColor(PostItColor.fromString(colorString));
        }

        postService.save(post);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{postId}/postit/{postItId}")
    public ResponseEntity<Void> deletePostIt(@PathVariable String postId, @PathVariable String postItId) {
        Post post = postService.findById(postId);

        PostIt matchPostIt = post.getPostIts().stream()
                .filter(postIt -> postIt.getId().equals(postItId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("PostIt not found"));

        post.getPostIts().remove(matchPostIt);
        postService.save(post);
        return ResponseEntity.noContent().build();
    }

    // TodoPost endpoints

    @PostMapping(value = "/{postId}/todopost")
    public ResponseEntity<Void> addTodoPost(@PathVariable String postId, @RequestBody TodoPost todoPost){
        Post post = postService.findById(postId);
        todoPost.setId(UUID.randomUUID().toString());
        post.getTodoPosts().add(todoPost);
        postService.save(post);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{postId}/todopost/{todoPostId}")
    public ResponseEntity<Void> updateTodoPost(@PathVariable String postId, @PathVariable String todoPostId,
                                               @RequestBody TodoPostUpdateDTO todoPostUpdateDTO) {
        Post post = postService.findById(postId);

        TodoPost matchTodo = post.getTodoPosts().stream()
                .filter(todoPost -> todoPost.getId().equals(todoPostId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("TodoPost not found"));
        matchTodo.setTitle(todoPostUpdateDTO.getTitle());
        postService.save(post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{postId}/todopost/{todoPostId}")
    public ResponseEntity<Void> deleteTodoPost(@PathVariable String postId, @PathVariable String todoPostId){
        Post post = postService.findById(postId);
        TodoPost matchTodo = post.getTodoPosts().stream()
                .filter(todoPost -> todoPost.getId().equals(todoPostId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("TodoPost not found"));
        post.getTodoPosts().remove(matchTodo);
        postService.save(post);
        return ResponseEntity.noContent().build();
    }

    // _Todo endpoints

    @PostMapping(value = "/{postId}/todopost/{todoPostId}/todo")
    public ResponseEntity<Void> addTodo(@PathVariable String postId, @PathVariable String todoPostId, @RequestBody Todo todo){
        Post post = postService.findById(postId);
        TodoPost matchTodo = post.getTodoPosts().stream()
                .filter(todoPost -> todoPost.getId().equals(todoPostId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("TodoPost not found"));
        todo.setId(UUID.randomUUID().toString());
        if(todo.getStatus() == null) {
            todo.setStatus(TodoStatus.TO_DO);
        }
        matchTodo.getTodos().add(todo);
        postService.save(post);
        return ResponseEntity.noContent().build();
    }
}
