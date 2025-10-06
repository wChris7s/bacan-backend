package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleDatabasePort {
  Flux<Role> findAllRoles();

  Mono<Role> findRoleById(Long roleId);
}






