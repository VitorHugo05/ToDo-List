package com.vitordev.todolist.services;

import com.vitordev.todolist.domain.post.Post;
import com.vitordev.todolist.repositories.PostRepository;
import com.vitordev.todolist.repositories.UserRepository;
import com.vitordev.todolist.services.exception.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findById(String postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ObjectNotFound("Post not found"));
    }


    public void delete(Post post) {
        postRepository.delete(post);
    }
}
