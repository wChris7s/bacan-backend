package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleUseCase {
  Flux<Role> getAllRoles();

  Mono<Role> getRoleById(Long id);
}
