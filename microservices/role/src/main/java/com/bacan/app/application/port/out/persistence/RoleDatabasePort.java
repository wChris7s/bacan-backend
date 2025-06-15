package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.model.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleDatabasePort {
  Mono<Role> createRole(Role role);

  Flux<Role> findAllRoles();

  Mono<Role> findRoleById(Long roleId);

  Flux<Role> findAllPublicRoles();
}






