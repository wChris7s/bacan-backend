package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.UserRoleUseCase;
import com.bacan.app.application.port.out.persistence.UserRoleDatabasePort;
import com.bacan.app.domain.user.User;
import com.bacan.app.domain.user.UserRole;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserRoleService implements UserRoleUseCase {
  private final UserRoleDatabasePort userRoleDatabasePort;

  public UserRoleService(UserRoleDatabasePort userRoleDatabasePort) {
    this.userRoleDatabasePort = userRoleDatabasePort;
  }

  @Override
  public Mono<Void> createUserRoles(User user) {
    return Flux.fromIterable(user.getRoles())
        .flatMap(role -> {
          UserRole userRole = UserRole.builder()
              .userId(user.getDocumentId())
              .roleId(role.getId())
              .build();
          return userRoleDatabasePort.createUserRole(userRole);
        }).then();
  }
}
