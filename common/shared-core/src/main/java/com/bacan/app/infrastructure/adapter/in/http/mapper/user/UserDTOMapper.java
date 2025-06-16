package com.bacan.app.infrastructure.adapter.in.http.mapper.user;

import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.CreateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UpdateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.address.AddressDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.mapper.role.RoleDTOMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDTOMapper {
  public static UserDTO mapToDto(User user) {
    return UserDTO.builder()
      .documentId(user.documentId())
      .name(user.name())
      .lastname(user.lastname())
      .birthDate(user.birthDate())
      .phone(user.phone())
      .email(user.email())
      .profilePhoto(user.photo())
      .createdAt(user.createdAt())
      .updatedAt(user.updatedAt())
      .build();
  }

  public static User mapToModel(String documentId, UpdateUserDTO user) {
    return User.builder()
      .documentId(documentId)
      .name(user.getName())
      .lastname(user.getLastname())
      .birthDate(user.getBirthdate())
      .phone(user.getPhone())
      .phoneCountryId(user.getPhoneCountryId())
      .address(AddressDTOMapper.mapToModel(documentId, user.getAddress()))
      .build();
  }

  public static User mapToModel(CreateUserDTO user) {
    return User.builder()
      .documentId(user.getDocumentId())
      .name(user.getName())
      .lastname(user.getLastname())
      .birthDate(user.getBirthdate())
      .phone(user.getPhone())
      .phoneCountryId(user.getPhoneCountryId())
      .email(user.getEmail())
      .password(user.getPassword())
      .role(RoleDTOMapper.mapToModel(user.getRoleId()))
      .address(AddressDTOMapper.mapToModel(user.getDocumentId(), user.getAddress()))
      .build();
  }
}
