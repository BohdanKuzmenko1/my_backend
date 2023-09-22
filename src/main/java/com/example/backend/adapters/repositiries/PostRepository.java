package com.example.backend.adapters.repositiries;

import com.example.backend.domain.entities.Post;
import com.example.backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostsByUser(User user);
}
