package com.example.backend.domain.usecases;

import com.example.backend.adapters.request_response.PostRequest;
import com.example.backend.domain.entities.Post;

public class CreatePostUseCase {
    public Post createPostUseCase(PostRequest request) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .build();
    }
}