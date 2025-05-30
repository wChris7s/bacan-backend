package com.bacan.app.infrastructure.adapter.out.persistence.persistence;

import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.domain.role.Role;
import com.bacan.app.infrastructure.adapter.out.persistence.persistence.entity.RoleEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.persistence.mapper.RoleEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.persistence.repository.RoleRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class RolePostgresAdapter implements RoleDatabasePort {

  private final RoleRepository roleRepository;

  public RolePostgresAdapter(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Mono<Role> createRole(Role role) {
    RoleEntity roleEntity = RoleEntityMapper.mapToEntity(role);
    return this.roleRepository.save(roleEntity)
        .map(RoleEntityMapper::mapToModel);
  }


  @Override
  public Flux<Role> findAllRoles() {
    return this.roleRepository.findAll()
        .map(RoleEntityMapper::mapToModel);
  }

  @Override
  public Mono<Long> countAllByIdIsIn(List<Long> roleIds) {
    return this.roleRepository.countAllByIdIsIn(roleIds);
  }
}
