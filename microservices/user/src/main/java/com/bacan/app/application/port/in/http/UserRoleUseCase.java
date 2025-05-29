package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.user.User;
import reactor.core.publisher.Mono;

public interface UserRoleUseCase {
  Mono<Void> createUserRoles(User user);
}
