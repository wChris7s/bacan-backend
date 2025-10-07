package com.bacan.app.application.services;

import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.domain.models.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

  private final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final UserDatabasePort userDatabasePort;

  @Override
  @Transactional
  public Mono<User> createUser(User user) {
    user.setEnabled(true);
    return userDatabasePort.createUser(user)
      .doOnSuccess(savedUser -> logger.info("User with document Id {} was created.", user.getDocumentId()))
      .doOnError(e -> logger.error("An error occurred while trying to create user: {}", e.getMessage()));
  }

  @Override
  @Transactional
  public Mono<User> updateUser(String documentId, User user) {
    return userDatabasePort.findUserByDocumentId(documentId)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("User not found"))))
      .map(savedUser -> {
        savedUser.setName(user.getName());
        savedUser.setLastname(user.getLastname());
        savedUser.setPhone(user.getPhone());
        savedUser.setEmail(user.getEmail());
        return savedUser;
      })
      .flatMap(userDatabasePort::updateUser)
      .doOnSuccess(savedUser -> logger.info("User with document Id {} was updated.", user.getDocumentId()))
      .doOnError(e -> logger.error("An error occurred while trying to update user: {}", e.getMessage()));
  }

  @Override
  public Flux<User> getAll() {
    return userDatabasePort.findAll();
  }

  @Override
  public Mono<User> getUserByIdOrThrow(Long userId) {
    return userDatabasePort.findUserById(userId)
      .switchIfEmpty(Mono.defer(() -> {
        logger.error("User with Id {} not found.", userId);
        throw new RuntimeException("User not found");
      }));
  }

  @Override
  public Mono<User> getUserByDocumentIdOrThrow(String documentId) {
    return userDatabasePort.findUserByDocumentId(documentId)
      .switchIfEmpty(Mono.defer(() -> {
        logger.error("User with document Id {} not found.", documentId);
        throw new RuntimeException("User not found");
      }));
  }
}
