package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserUseCase {
    Mono<Void> createUser(User user);

    Flux<User> getAllUser();
}
