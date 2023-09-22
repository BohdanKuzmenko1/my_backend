package com.example.backend.domain.services;

import com.example.backend.adapters.dto.PostDTO;
import com.example.backend.adapters.mappers.CustomModelMapper;
import com.example.backend.adapters.repositiries.PostRepository;
import com.example.backend.domain.entities.Post;
import com.example.backend.domain.entities.User;
import com.example.backend.domain.exceptions.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final CustomModelMapper<Post, PostDTO> modelMapper;

    /*
        public List<PostDTO> findPostsByUserEmail(String email) {
        User user = userService.findUserByEmail(email);
        List<Post> posts = postRepository.findPostsByUser(user);
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }*/

    public List<Post> findPostsByUserEmail(String email) {
        return postRepository.findPostsByUser(userService.findUserByEmail(email));
    }

    
    public Post findPostByUserEmailAndId(String email, int id) {
        List<Post> posts = findPostsByUserEmail(email);
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        throw new PostNotFoundException
                (HttpStatus.NOT_FOUND, "User \"" + email + "\" does not have post with id " + id + ".");
    }

    public Post updatePostByFields(String email , int postId, Map<String, Object> postDetails) {
        Post post = findPostByUserEmailAndId(email, postId);
        postDetails.forEach((key, value) -> {
            switch (key) {
                case "title" -> post.setTitle((String) value);
                case "content" -> post.setContent((String) value);
            }
        });
        return postRepository.save(post);
    }
    public PostDTO addPostToTheListByUserEmail(String email, Post post) {
        User user = userService.findUserByEmail(email);
        post.setUser(user);
        return modelMapper.toDTO(postRepository.save(post), PostDTO.class);
    }
}
