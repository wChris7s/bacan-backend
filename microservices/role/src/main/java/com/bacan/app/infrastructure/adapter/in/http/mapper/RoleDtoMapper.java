package com.bacan.app.infrastructure.adapter.in.http.mapper;

import com.bacan.app.domain.role.Role;
import com.bacan.app.infrastructure.adapter.in.http.dto.CreateRoleDto;
import com.bacan.app.infrastructure.adapter.in.http.dto.RoleDto;

public class RoleDtoMapper {
  public static RoleDto mapToDto(Role role) {
    return RoleDto.builder()
        .id(role.getId())
        .name(role.getName())
        .createdAt(role.getCreatedAt())
        .updatedAt(role.getUpdatedAt())
        .enabled(role.isEnabled())
        .build();
  }

  public static Role mapToModel(CreateRoleDto role) {
    return Role.builder()
        .name(role.getName())
        .build();
  }
}
