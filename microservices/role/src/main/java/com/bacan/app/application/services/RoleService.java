package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.RoleUseCase;
import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.domain.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

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
  public Mono<Void> validateRoles(List<Long> roleIds) {
    return this.roleDatabasePort.countAllByIdIsIn(roleIds)
        .flatMap(total -> {
          if (roleIds.size() == total.intValue()) {
            return Mono.empty();
          }
          return Mono.error(() -> new RuntimeException("One or more roles are invalid."));
        });
  }

}
