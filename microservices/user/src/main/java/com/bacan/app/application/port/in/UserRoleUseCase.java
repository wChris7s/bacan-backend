package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.user.User;
import com.bacan.app.domain.model.user.UserRole;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRoleUseCase {
  Mono<Void> createUserRoles(User user);
}
