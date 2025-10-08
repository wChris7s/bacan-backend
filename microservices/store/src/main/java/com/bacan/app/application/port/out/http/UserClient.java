package com.bacan.app.application.port.out.http;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Mono;

public interface UserClient {
  Mono<User> getUserByDocumentId(String documentId);

  Mono<User> getUserById(Long userId);
}
