package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Mono;

public interface UserDatabasePort {
  Mono<User> createUser(User user);

  Mono<User> findUserById(String userId);
}






