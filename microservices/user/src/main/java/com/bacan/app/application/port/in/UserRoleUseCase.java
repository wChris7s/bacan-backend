package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.user.User;
import com.bacan.app.domain.model.user.UserRole;
import reactor.core.publisher.Flux;

public interface UserRoleUseCase {
  Flux<UserRole> createUserRoles(User user);
}
