package com.bacan.app.application.services;

import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.constant.UserStorage;
import com.bacan.app.domain.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
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
    return this.userDatabasePort.createUser(user
        .withPhoto(UserStorage.DEFAULT_PROFILE_PHOTO)
        .withEnabled(true)
        .withCreatedAt(actualDateTime)
        .withUpdatedAt(actualDateTime)
        .withPasswordModifiedDate(actualDateTime))
      .doOnSuccess(savedUser -> logger.info("User with ID {} was created.", user.documentId()))
      .doOnError(e -> logger.error("Error details: {}", e.getMessage()));
  }

  @Override
  public Flux<User> getAllUser() {
    return this.userDatabasePort.findAllUsers();
  }
}
