package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.model.user.UserRole;
import reactor.core.publisher.Mono;

public interface UserRoleDatabasePort {
  Mono<UserRole> createUserRole(UserRole userRole);
}
