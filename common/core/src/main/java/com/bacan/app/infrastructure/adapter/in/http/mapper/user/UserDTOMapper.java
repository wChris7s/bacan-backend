package com.bacan.app.infrastructure.adapter.in.http.mapper.user;

import com.bacan.app.domain.models.role.Role;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.CreateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UpdateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.address.AddressDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
  uses = {
    AddressDTOMapper.class,
  }
)
public interface UserDTOMapper {
  @Mapping(target = "address", source = "address")
  @Mapping(target = "role", source = "roleId")
  @Mapping(target = "profilePhotoUrl", ignore = true)
  /* Auditable */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  User map(CreateUserDTO dto);

  @Mapping(target = "address", source = "address")
  @Mapping(target = "documentId", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "profilePhotoUrl", ignore = true)
  /* Auditable */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  User map(UpdateUserDTO dto);

  UserDTO map(User dto);

  default Role map(Long roleId) {
    return Role.builder()
      .id(roleId)
      .build();
  }
}
