package com.bacan.app.application.port.out.http;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Mono;

public interface UserMicroservice {
  Mono<User> getUserById(String userId);
}
