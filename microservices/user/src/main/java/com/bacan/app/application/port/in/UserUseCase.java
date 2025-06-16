package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserUseCase {
  Mono<User> createUser(User user);

  Flux<User> getAllUser();
}
