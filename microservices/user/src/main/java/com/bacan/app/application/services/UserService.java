package com.bacan.app.application.services;

import com.bacan.app.application.port.in.http.UserUseCase;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class UserService implements UserUseCase {
  private final UserDatabasePort userDatabasePort;

  public UserService(UserDatabasePort userDatabasePort) {
    this.userDatabasePort = userDatabasePort;
  }

  @Override
  public Mono<User> createUser(User user) {
    return this.userDatabasePort.createUser(user
        .withEnabled(true)
        .withCreatedAt(LocalDateTime.now())
        .withUpdatedAt(LocalDateTime.now())
    );
  }

  @Override
  public Flux<User> getAllUser() {
    return this.userDatabasePort.findAllUsers();
  }
}
