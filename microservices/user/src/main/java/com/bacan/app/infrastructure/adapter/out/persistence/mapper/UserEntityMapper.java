package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.user.User;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserEntity;

public class UserEntityMapper {
  public static UserEntity mapToEntity(User user) {
    return UserEntity.builder()
      .documentId(user.documentId())
      .name(user.name())
      .lastname(user.lastname())
      .birthdate(user.birthDate())
      .phone(user.phone())
      .email(user.email())
      .password(user.password())
      .photo(user.photo())
      .createdAt(user.createdAt())
      .updatedAt(user.updatedAt())
      .enabled(user.enabled())
      .passwordModifiedDate(user.passwordModifiedDate())
      .phoneCountryId(user.phoneCountryId())
      .build();
  }

  public static User mapToModel(UserEntity user) {
    return User.builder()
      .documentId(user.getDocumentId())
      .name(user.getName())
      .lastname(user.getLastname())
      .birthDate(user.getBirthdate())
      .phone(user.getPhone())
      .email(user.getEmail())
      .password(user.getPassword())
      .photo(user.getPhoto())
      .createdAt(user.getCreatedAt())
      .updatedAt(user.getUpdatedAt())
      .enabled(user.isEnabled())
      .passwordModifiedDate(user.getPasswordModifiedDate())
      .phoneCountryId(user.getPhoneCountryId())
      .build();
  }
}
