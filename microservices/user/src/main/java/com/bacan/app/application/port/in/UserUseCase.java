package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserUseCase {
  Mono<User> createUser(User user);

  Mono<User> updateUser(String documentId, User user);

  Flux<User> getAll();

  Mono<User> getUserByIdOrThrow(Long userId);

  Mono<User> getUserByDocumentIdOrThrow(String documentId);
}
