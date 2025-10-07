package com.bacan.app.application.services;

import com.bacan.app.application.port.in.RoleUseCase;
import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.domain.models.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleUseCase {

  private final RoleDatabasePort roleDatabasePort;

  @Override
  public Flux<Role> getAllRoles() {
    return this.roleDatabasePort.findAllRoles();
  }

  @Override
  public Mono<Role> getRoleById(Long id) {
    return this.roleDatabasePort.findRoleById(id);
  }
}
