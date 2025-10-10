package com.bacan.app.application.facade;

import com.bacan.app.application.port.out.http.LocationClient;
import com.bacan.app.application.services.AddressService;
import com.bacan.app.application.services.RoleService;
import com.bacan.app.application.services.UserService;
import com.bacan.app.domain.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserFacade {
  private final AddressService addressService;

  private final RoleService roleService;

  private final UserService userService;

  private final LocationClient locationClient;

  @Transactional
  public Mono<User> createUser(User user) {
    user.setProfilePhotoUrl("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
    return roleService.getRoleById(user.getRole().getId())
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Role not found."))))
      .then(locationClient.getFullDistrictById(user.getAddress().getDistrict().id()))
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Location not found."))))
      .then(addressService.createUserAddress(user.getAddress()))
      .flatMap(savedAddress -> {
        user.getAddress().setId(savedAddress.getId());
        user.setAddress(savedAddress);
        return userService.createUser(user);
      });
  }

  @Transactional
  public Mono<User> updateUser(String documentId, User user) {
    return locationClient.getFullDistrictById(user.getAddress().getDistrict().id())
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Location not found."))))
      .then(addressService.updateUserAddress(user.getAddress().getId(), user.getAddress()))
      .then(userService.updateUser(documentId, user));
  }
}
