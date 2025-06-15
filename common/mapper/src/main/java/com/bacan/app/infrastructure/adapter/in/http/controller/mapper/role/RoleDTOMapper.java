package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.role;

import com.bacan.app.domain.model.role.Role;
import com.bacan.app.infrastructure.adapter.in.http.dto.role.CreateRoleDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.role.RoleDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleDTOMapper {
  public static RoleDTO mapToDto(Role role) {
    return RoleDTO.builder()
      .id(role.getId())
      .name(role.getName())
      .build();
  }

  public static Role mapToModel(CreateRoleDTO role) {
    return Role.builder()
      .name(role.getName())
      .build();
  }

  public static Role mapToModel(Long roleId) {
    return Role.builder()
      .id(roleId)
      .build();
  }
}
