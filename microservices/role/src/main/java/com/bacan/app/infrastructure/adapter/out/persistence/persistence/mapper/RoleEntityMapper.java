package com.bacan.app.infrastructure.adapter.out.persistence.persistence.mapper;

import com.bacan.app.domain.role.Role;
import com.bacan.app.infrastructure.adapter.out.persistence.persistence.entity.RoleEntity;

public class RoleEntityMapper {
    public static RoleEntity mapToEntity(Role role) {
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .enabled(role.isEnabled())
                .build();
    }

    public static Role mapToModel(RoleEntity role) {
        return Role.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .enabled(role.isEnabled())
                .build();
    }
}
