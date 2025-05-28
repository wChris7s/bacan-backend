package com.bacan.app.infrastructure.adapter.out.persistence.mapper;
import com.bacan.app.domain.user.User;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserEntity;

import java.time.LocalDateTime;

public class UserEntityMapper {
    public  static UserEntity mapToEntity(User user){
        return UserEntity.builder()
                .documentId(user.getDocumentId())
                .name(user.getName())
                .lastname(user.getLastname())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(user.getPassword())
                .profilePhoto(user.getProfilePhoto())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .enabled(user.isEnabled())
                .build();
    }
    public static User mapToModel(UserEntity user){
        return User.builder()
                .documentId(user.getDocumentId())
                .name(user.getName())
                .lastname(user.getLastname())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(user.getPassword())
                .profilePhoto(user.getProfilePhoto())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .enabled(user.isEnabled())
                .build();
    }
}
