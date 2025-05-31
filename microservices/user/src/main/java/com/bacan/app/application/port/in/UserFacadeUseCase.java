package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.user.User;
import reactor.core.publisher.Mono;

public interface UserFacadeUseCase {
  Mono<Void> createUserAndAssignTheirRoles(User user);
}
