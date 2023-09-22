package com.example.backend.adapters.dto;

import com.example.backend.domain.entities.Post;
import com.example.backend.domain.entities.ProfileImage;
import com.example.backend.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private ProfileImage profileImage;
    private List<Post> posts;
    private Role role;
}
