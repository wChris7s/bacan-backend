package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.UserFacadeUseCase;
import com.bacan.app.application.port.in.UserRoleUseCase;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.http.RolePort;
import com.bacan.app.domain.model.role.Role;
import com.bacan.app.domain.model.user.User;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserFacade implements UserFacadeUseCase {
  private final UserUseCase userUseCase;
  private final UserRoleUseCase userRoleUseCase;
  private final RolePort rolePort;

  public UserFacade(
      UserUseCase userUseCase,
      UserRoleUseCase userRoleUseCase,
      RolePort rolePort) {
    this.userUseCase = userUseCase;
    this.userRoleUseCase = userRoleUseCase;
    this.rolePort = rolePort;
  }

  @Transactional
  public Mono<Void> createUserAndAssignTheirRoles(User user) {
    return Flux.fromIterable(user.roles())
        .map(Role::getId)
        .collectList()
        .flatMap(rolePort::validateRoleIds)
        .then(Mono.defer(() -> userUseCase.createUser(user)))
        .flatMap(savedUser -> userRoleUseCase.createUserRoles(savedUser).then());
  }
}
