package com.bacan.app.application.services;

import com.bacan.app.application.port.in.UserRoleUseCase;
import com.bacan.app.application.port.out.persistence.UserRoleDatabasePort;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.domain.models.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class UserRoleService implements UserRoleUseCase {
  private final Logger logger = LoggerFactory.getLogger(UserRoleService.class);
  private final UserRoleDatabasePort userRoleDatabasePort;

  public UserRoleService(UserRoleDatabasePort userRoleDatabasePort) {
    this.userRoleDatabasePort = userRoleDatabasePort;
  }

  @Override
  public Mono<Void> createUserRoles(User user) {
    return userRoleDatabasePort.createUserRole(UserRole.builder()
        .userId(user.documentId())
        .roleId(user.role().getId())
        .build())
      .doOnSuccess(unused -> logger.info("User's role for user ID {} was created.", user.documentId()))
      .doOnError(e -> logger.error("Error details: {}", e.getMessage()))
      .then();
  }
}
