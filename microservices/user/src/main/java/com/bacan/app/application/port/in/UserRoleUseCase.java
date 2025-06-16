package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.user.User;
import reactor.core.publisher.Mono;

public interface UserRoleUseCase {
  Mono<Void> createUserRoles(User user);
}
