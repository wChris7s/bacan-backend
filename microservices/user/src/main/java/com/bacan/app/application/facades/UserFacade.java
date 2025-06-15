package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.AddressUseCase;
import com.bacan.app.application.port.in.UserFacadeUseCase;
import com.bacan.app.application.port.in.UserRoleUseCase;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.http.MediaPort;
import com.bacan.app.application.port.out.http.RolePort;
import com.bacan.app.domain.model.user.User;
import com.bacan.app.domain.storage.DefaultStorageType;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

public class UserFacade implements UserFacadeUseCase {
  private final UserUseCase userUseCase;
  private final UserRoleUseCase userRoleUseCase;
  private final RolePort rolePort;
  private final AddressUseCase addressUseCase;
  private final MediaPort mediaPort;

  public UserFacade(
    UserUseCase userUseCase,
    UserRoleUseCase userRoleUseCase,
    RolePort rolePort,
    AddressUseCase addressUseCase, MediaPort mediaPort) {
    this.userUseCase = userUseCase;
    this.userRoleUseCase = userRoleUseCase;
    this.rolePort = rolePort;
    this.addressUseCase = addressUseCase;
    this.mediaPort = mediaPort;
  }

  @Transactional
  public Mono<Void> createUserAndAssignTheirRoles(User user) {
    return rolePort.validateRole(user.role().getId())
      .then(Mono.defer(() -> mediaPort.storeDefaultFileAndGetFilename(DefaultStorageType.USER)))
      .flatMap(filename -> userUseCase.createUser(user.withPhoto(filename)))
      .then(Mono.defer(() -> addressUseCase.createUserAddress(user.address())))
      .then(Mono.defer(() -> userRoleUseCase.createUserRoles(user)));
  }
}
