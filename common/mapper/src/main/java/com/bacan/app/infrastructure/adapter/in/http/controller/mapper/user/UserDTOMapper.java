package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.user;

import com.bacan.app.domain.model.user.User;
import com.bacan.app.infrastructure.adapter.in.http.controller.mapper.address.AddressDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.controller.mapper.role.RoleDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.CreateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
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
      .profilePhoto(user.profilePhoto())
      .createdAt(user.createdAt())
      .updatedAt(user.updatedAt())
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
