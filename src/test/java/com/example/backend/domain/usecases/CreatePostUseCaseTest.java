package com.example.backend.domain.usecases;

import com.example.backend.adapters.request_response.PostRequest;
import com.example.backend.domain.entities.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreatePostUseCaseTest {

    @Mock
    private CreatePostUseCase createPostUseCase;

    @Test
    public void testCreatePost() {
        // GIVEN
        PostRequest request = new PostRequest(
                "user@example.com",
                "My Title",
                "My Content");
        Post expectedPost = Post.builder()
                .title("My Title")
                .content("My Content")
                .build();

        when(createPostUseCase.createPostUseCase(request)).thenReturn(expectedPost);

        // WHEN
        Post actualPost = createPostUseCase.createPostUseCase(request);

        // THEN
        assertEquals(expectedPost.getTitle(), actualPost.getTitle());
        assertEquals(expectedPost.getContent(), actualPost.getContent());
    }
}