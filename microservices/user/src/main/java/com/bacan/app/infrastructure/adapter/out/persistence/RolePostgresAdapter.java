package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.domain.models.role.Role;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.RoleEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RolePostgresAdapter implements RoleDatabasePort {

  private final RoleRepository roleRepository;

  static final RoleEntityMapper roleEntityMapper = Mappers.getMapper(RoleEntityMapper.class);

  @Override
  public Flux<Role> findAllRoles() {
    return this.roleRepository.findAll()
      .map(roleEntityMapper::mapToModel);
  }

  @Override
  public Mono<Role> findRoleById(Long roleId) {
    return this.roleRepository.findById(roleId)
      .map(roleEntityMapper::mapToModel);
  }
}
