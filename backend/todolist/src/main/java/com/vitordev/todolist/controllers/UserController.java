package com.vitordev.todolist.controllers;

import com.vitordev.todolist.domain.post.Post;
import com.vitordev.todolist.domain.post.dto.PostDTO;
import com.vitordev.todolist.domain.user.User;
import com.vitordev.todolist.domain.user.dto.UserDTO;
import com.vitordev.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTO = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping(value = "/posts/{userId}")
    public ResponseEntity<List<PostDTO>> findAllPostsByUser(@PathVariable String userId) {
        List<Post> postByUser = userService.findAllPostsById(userId);
        List<PostDTO> postDTOS = postByUser.stream().map(PostDTO::new).toList();
        return ResponseEntity.ok().body(postDTOS);
    }
}
