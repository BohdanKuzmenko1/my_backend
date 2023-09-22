package com.example.backend.domain.services;

import com.example.backend.adapters.dto.ProfileImageDTO;
import com.example.backend.adapters.mappers.CustomModelMapper;
import com.example.backend.adapters.repositiries.ProfileImageRepository;
import com.example.backend.adapters.repositiries.UserRepository;
import com.example.backend.adapters.request_response.ProfileImageRequest;
import com.example.backend.domain.entities.ProfileImage;
import com.example.backend.domain.entities.User;
import com.example.backend.domain.exceptions.ProfileImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileImageService {
    private final CustomModelMapper<ProfileImage, ProfileImageDTO> modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;


    public ProfileImage findImageByUserEmail(String email) {
        User user = userService.findUserByEmail(email);
        return profileImageRepository.findProfileImageById(user.getId())
                .orElseThrow(() -> new ProfileImageNotFoundException(HttpStatus.NO_CONTENT, "Profile image not found exception"));
    }

    public ProfileImageDTO updateProfileImage(ProfileImageRequest profileImageRequest) {
        ProfileImage profileImage = findImageByUserEmail(profileImageRequest.userEmail());
        profileImage.setName(profileImageRequest.name());
        profileImage.setOriginalFileName(profileImageRequest.originalFileName());
        profileImage.setBytes(profileImageRequest.bytes());
        profileImage.setContentType(profileImageRequest.contentType());
        profileImage.setSize(profileImageRequest.size());
        profileImageRepository.save(profileImage);
        return modelMapper.toDTO(profileImage, ProfileImageDTO.class);
    }

    public static ProfileImage getDefaultProfileImage() {
        Resource resource = new ClassPathResource("static/images/ProfileImage.jpg");
        try {
            byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
            ProfileImage profileImage = new ProfileImage();
            profileImage.setName("ProfileImage.jpg");
            profileImage.setContentType("image/jpeg");
            profileImage.setOriginalFileName("ProfileImage.jpg");
            profileImage.setSize(resource.contentLength());
            profileImage.setBytes(bytes);
            return profileImage;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public void deleteProfileImageByUserEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow();
        profileImageRepository.deleteProfileImageById(user.getId());
        user.setProfileImage(getDefaultProfileImage());
        userRepository.save(user);
    }
}
