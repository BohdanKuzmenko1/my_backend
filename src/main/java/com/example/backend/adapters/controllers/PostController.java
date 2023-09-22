package com.example.backend.adapters.controllers;

import com.example.backend.adapters.dto.PostDTO;
import com.example.backend.adapters.mappers.CustomModelMapper;
import com.example.backend.adapters.request_response.PostRequest;
import com.example.backend.domain.entities.Post;
import com.example.backend.domain.services.PostService;
import com.example.backend.domain.usecases.CreatePostUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {
    private final PostService postService;
    private final CreatePostUseCase createPostUseCase;
    private final CustomModelMapper<Post, PostDTO> modelMapper;
    private final String getAllPostsByUserEmailDescription = "This request retrieves all posts that are linked to a user using email.";
    private final String createPostDescription = "This request creates post and adds it to database.";
    @Operation(
            description = getAllPostsByUserEmailDescription,
            summary = "Get all posts by user email."
    )
    @GetMapping()
    public ResponseEntity<List<PostDTO>> getAllPostsByUserEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(modelMapper.listToDTO(
                postService.findPostsByUserEmail(email), PostDTO.class));
    }

    @Operation(
            description = createPostDescription,
            summary = "Create new post."
    )
    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.addPostToTheListByUserEmail(
                request.userEmail(),
                createPostUseCase.createPostUseCase(request)));
    }
    @PatchMapping
    public ResponseEntity<PostDTO> updatePostByFields(@RequestParam(name = "email") String email,
                                                      @RequestParam(name = "id") int id,
                                                      @RequestBody Map<String, Object> postDetails) {
        return ResponseEntity.ok(modelMapper
                .toDTO(postService.updatePostByFields(email, id, postDetails),
                        PostDTO.class));
    }
}
