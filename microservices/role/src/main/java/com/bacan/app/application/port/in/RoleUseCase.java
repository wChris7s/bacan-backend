package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleUseCase {
  Mono<Role> createRole(Role role);

  Flux<Role> getAllRoles();

  Flux<Role> getAllPublicRoles();

  Mono<Void> validateRole(Long roleId);
}
