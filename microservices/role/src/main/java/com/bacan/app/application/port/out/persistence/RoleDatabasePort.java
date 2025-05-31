package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.model.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleDatabasePort {
  Mono<Role> createRole(Role role);

  Flux<Role> findAllRoles();

  Mono<Long> countAllByIdIsIn(List<Long> roleIds);
}






