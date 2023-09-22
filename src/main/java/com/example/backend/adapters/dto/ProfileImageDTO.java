package com.example.backend.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageDTO {
    private Integer id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private byte[] bytes;
}
