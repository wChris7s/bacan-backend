package com.bacan.app.application.services;

import com.bacan.app.application.port.in.UserRoleUseCase;
import com.bacan.app.application.port.out.persistence.UserRoleDatabasePort;
import com.bacan.app.domain.model.user.User;
import com.bacan.app.domain.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class UserRoleService implements UserRoleUseCase {
  private final Logger logger = LoggerFactory.getLogger(UserRoleService.class);
  private final UserRoleDatabasePort userRoleDatabasePort;

  public UserRoleService(UserRoleDatabasePort userRoleDatabasePort) {
    this.userRoleDatabasePort = userRoleDatabasePort;
  }

  @Override
  public Flux<UserRole> createUserRoles(User user) {
    return Flux.fromIterable(user.roles())
        .flatMap(role -> {
          UserRole userRole = new UserRole(user.documentId(), role.getId());
          return userRoleDatabasePort.createUserRole(userRole);
        })
        .doOnComplete(() -> logger.info("User's roles for user ID {} was created.", user.documentId()))
        .doOnError(e -> logger.error("Error details: {}", e.getMessage()));
  }
}
