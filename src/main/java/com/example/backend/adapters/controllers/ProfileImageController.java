package com.example.backend.adapters.controllers;

import com.example.backend.adapters.dto.ProfileImageDTO;
import com.example.backend.adapters.request_response.ProfileImageRequest;
import com.example.backend.domain.entities.ProfileImage;
import com.example.backend.domain.services.ProfileImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile-image")
@Tag(name = "Profile Image")
public class ProfileImageController {
    private final ProfileImageService profileImageService;
    private final String getImageDescription = "This request retrieves byte array of profile image that finds by user email.";
    private final String updateImageDescription = "This http method replaces the old profile image with a new one using variables passed in the json body.";
    private final String deleteProfileImageDescription = "Deletes profile photo and sets a default one.";
    @Operation(
            description = getImageDescription,
            summary = "Get profile image."
    )
    @GetMapping()
    public ResponseEntity<byte[]> getImage(@RequestParam(name = "email") String email) {
        ProfileImage profileImage = profileImageService.findImageByUserEmail(email);
        return ResponseEntity.ok(profileImage.getBytes());
    }

    @Operation(
            description = updateImageDescription,
            summary = "Put new profile image."
    )
    @PutMapping()
    public ResponseEntity<ProfileImageDTO> updateImage(
            @RequestBody ProfileImageRequest profileImageRequest) {
        return ResponseEntity.ok(profileImageService.updateProfileImage(profileImageRequest));
    }

    @Operation(
            description = deleteProfileImageDescription,
            summary = "Delete you profile image."
    )
    @DeleteMapping()
    public ResponseEntity<String> deleteProfileImage(@RequestParam String email) {
           profileImageService.deleteProfileImageByUserEmail(email);
           return ResponseEntity.ok("Profile image was successfully reset.");
    }
}
