package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDatabasePort {
  Mono<User> createUser(User user);

  Mono<User> updateUser(User user);

  Flux<User> findAll();

  Mono<User> findUserById(Long userId);

  Mono<User> findUserByDocumentId(String documentId);
}






