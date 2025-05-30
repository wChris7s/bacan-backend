package com.bacan.app.infrastructure.adapter.in.http.mapper;

import com.bacan.app.domain.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.CreateUserDto;
import com.bacan.app.infrastructure.adapter.in.http.dto.UserDto;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserEntity;

public class UserDtoMapper {
  public static UserDto mapToDto(User user) {
    return UserDto.builder()
        .documentId(user.getDocumentId())
        .name(user.getName())
        .lastname(user.getLastname())
        .birthDate(user.getBirthDate())
        .phone(user.getPhone())
        .email(user.getEmail())
        .profilePhoto(user.getProfilePhoto())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .enabled(user.isEnabled())
        .build();
  }
  public static User mapToModel(CreateUserDto user) {
    return User.builder()
        .name(user.getName())
        .build();
  }
}
