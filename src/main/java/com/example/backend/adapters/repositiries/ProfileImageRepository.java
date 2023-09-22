package com.example.backend.adapters.repositiries;

import com.example.backend.domain.entities.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer> {
    Optional<ProfileImage> findProfileImageById(int id);
    void deleteProfileImageById(int id);
}
