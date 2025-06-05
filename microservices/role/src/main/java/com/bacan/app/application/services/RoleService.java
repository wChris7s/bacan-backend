package com.bacan.app.application.services;

import com.bacan.app.application.port.in.RoleUseCase;
import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.domain.model.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class RoleService implements RoleUseCase {
  private final RoleDatabasePort roleDatabasePort;

  public RoleService(RoleDatabasePort roleDatabasePort) {
    this.roleDatabasePort = roleDatabasePort;
  }

  @Override
  public Mono<Role> createRole(Role role) {
    return this.roleDatabasePort.createRole(role
      .withEnabled(true)
      .withCreatedAt(LocalDateTime.now())
      .withUpdatedAt(LocalDateTime.now())
    );
  }

  @Override
  public Flux<Role> getAllRoles() {
    return this.roleDatabasePort.findAllRoles();
  }

  @Override
  public Flux<Role> getAllPublicRoles() {
    return this.roleDatabasePort.findAllPublicRoles();
  }

  @Override
  public Mono<Void> validateRole(Long roleId) {
    return this.roleDatabasePort.findRoleById(roleId)
      .switchIfEmpty(Mono.error(() -> new RuntimeException("Role with ID " + roleId + " not found.")))
      .then();
  }
}
