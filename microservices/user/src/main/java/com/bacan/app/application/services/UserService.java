package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.UserRoleUseCase;
import com.bacan.app.application.port.in.http.UserUseCase;
import com.bacan.app.application.port.out.http.RolePort;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.role.Role;
import com.bacan.app.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


public class UserService implements UserUseCase {
  private final Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserRoleUseCase userRoleUseCase;
  private final UserDatabasePort userDatabasePort;
  private final RolePort rolePort;

  public UserService(
      UserRoleUseCase userRoleUseCase,
      UserDatabasePort userDatabasePort,
      RolePort rolePort) {
    this.userRoleUseCase = userRoleUseCase;
    this.userDatabasePort = userDatabasePort;
    this.rolePort = rolePort;
  }

  @Override
  public Mono<Void> createUser(User user) {
    return Flux.fromIterable(user.getRoles())
        .map(Role::getId)
        .collectList()
        .flatMap(this.rolePort::validateRoleIds)
        .then(Mono.defer(() -> this.userDatabasePort.createUser(user
                .withEnabled(true)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now()))
            .flatMap(userRoleUseCase::createUserRoles)))
        .doOnSuccess(savedUser -> logger.info("User with ID {} was created.", user.getDocumentId()))
        .doOnError(e -> logger.error("Error details: {}", e.getMessage()))
        .onErrorResume(e -> Mono.error(new RuntimeException("Error creating user")));
  }

  @Override
  public Flux<User> getAllUser() {
    return this.userDatabasePort.findAllUsers();
  }
}
