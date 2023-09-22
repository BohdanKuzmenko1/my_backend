package com.example.backend.adapters.controllers;

import com.example.backend.adapters.dto.UserDTO;
import com.example.backend.adapters.mappers.CustomModelMapper;
import com.example.backend.domain.entities.User;
import com.example.backend.domain.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User")
public class UserController {
    private final UserService userService;
    private final CustomModelMapper<User, UserDTO> modelMapper;
    @GetMapping
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(modelMapper.toDTO(userService.findUserByEmail(email), UserDTO.class));
    }
    @DeleteMapping
    public ResponseEntity<UserDTO> deleteUser(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(modelMapper.toDTO(userService.deleteUserByEmail(email), UserDTO.class));
    }
}
