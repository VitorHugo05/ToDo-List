package com.vitordev.todolist.services;

import com.vitordev.todolist.domain.post.Post;
import com.vitordev.todolist.domain.user.User;
import com.vitordev.todolist.repositories.UserRepository;
import com.vitordev.todolist.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Post> findAllPostsById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFound("User not found"));
        return user.getPosts();
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFound("User not found"));
    }
}
