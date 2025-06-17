package com.bacan.app.application.services;

import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.models.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;


public class UserService implements UserUseCase {
  private final Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserDatabasePort userDatabasePort;

  public UserService(UserDatabasePort userDatabasePort) {
    this.userDatabasePort = userDatabasePort;
  }

  @Override
  public Mono<User> createUser(User user) {
    LocalDateTime actualDateTime = LocalDateTime.now(ZoneId.of("America/Lima"));
    return userDatabasePort.createUser(user
        .withEnabled(true)
        .withCreatedAt(actualDateTime)
        .withUpdatedAt(actualDateTime)
        .withPasswordModifiedDate(actualDateTime))
      .doOnSuccess(savedUser -> logger.info("User with ID {} was created.", user.documentId()))
      .doOnError(e -> logger.error("Error details: {}", e.getMessage()));
  }

  @Override
  public Mono<User> getUserById(String userId) {
    return userDatabasePort.findUserById(userId)
      .switchIfEmpty(Mono.defer(() -> {
        logger.error("User with Id {} not found.", userId);
        throw new RuntimeException("User not found");
      }));
  }
}
