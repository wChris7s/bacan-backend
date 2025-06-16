package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.UserRoleDatabasePort;
import com.bacan.app.domain.models.user.UserRole;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.UserRoleEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.UserRoleEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.UserRoleRepository;
import reactor.core.publisher.Mono;

public class UserRolePostgresAdapter implements UserRoleDatabasePort {
  private final UserRoleRepository userRoleRepository;

  public UserRolePostgresAdapter(UserRoleRepository userRoleRepository) {
    this.userRoleRepository = userRoleRepository;
  }

  @Override
  public Mono<Void> createUserRole(UserRole userRole) {
    UserRoleEntity userRoleEntity = UserRoleEntityMapper.mapToEntity(userRole);
    return this.userRoleRepository.createUserRole(userRoleEntity);
  }
}
