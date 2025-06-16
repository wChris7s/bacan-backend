package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.user.UserRole;
import reactor.core.publisher.Mono;

public interface UserRoleDatabasePort {
  Mono<Void> createUserRole(UserRole userRole);
}
