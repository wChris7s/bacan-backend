package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.user.UserRole;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserRoleEntity;

public class UserRoleEntityMapper {
  public static UserRoleEntity mapToEntity(UserRole userRole) {
    return UserRoleEntity.builder()
        .userId(userRole.userId())
        .rolId(userRole.userId())
        .build();
  }

  public static UserRole mapToModel(UserRoleEntity userRole) {
    return UserRole.builder()
        .userId(userRole.getUserId())
        .roleId(userRole.getUserId())
        .build();
  }
}