package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.models.role.Role;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleEntityMapper {
  Role mapToModel(RoleEntity role);
}
