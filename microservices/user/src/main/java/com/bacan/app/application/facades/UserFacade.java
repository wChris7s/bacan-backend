package com.bacan.app.application.facades;

import com.bacan.app.application.port.in.AddressUseCase;
import com.bacan.app.application.port.in.UserFacadeUseCase;
import com.bacan.app.application.port.in.UserRoleUseCase;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.http.FileManager;
import com.bacan.app.application.port.out.http.RolePort;
import com.bacan.app.domain.enums.DefaultStorageType;
import com.bacan.app.domain.models.user.User;
import lombok.Builder;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Builder
public class UserFacade implements UserFacadeUseCase {
  private final UserUseCase userUseCase;
  private final UserRoleUseCase userRoleUseCase;
  private final RolePort rolePort;
  private final AddressUseCase addressUseCase;
  private final FileManager fileManager;

  public UserFacade(
    UserUseCase userUseCase,
    UserRoleUseCase userRoleUseCase,
    RolePort rolePort,
    AddressUseCase addressUseCase, FileManager fileManager) {
    this.userUseCase = userUseCase;
    this.userRoleUseCase = userRoleUseCase;
    this.rolePort = rolePort;
    this.addressUseCase = addressUseCase;
    this.fileManager = fileManager;
  }

  @Transactional
  public Mono<Void> createUserAndAssignTheirRoles(User user) {
    return rolePort.validateRole(user.role().getId())
      .then(Mono.defer(() -> fileManager.storeDefaultFileAndGetFilename(DefaultStorageType.USER)))
      .flatMap(filename -> userUseCase.createUser(user.withPhoto(filename)))
      .then(Mono.defer(() -> addressUseCase.createUserAddress(user.address())))
      .then(Mono.defer(() -> userRoleUseCase.createUserRoles(user)));
  }
}
