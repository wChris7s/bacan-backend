package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.UserFacadeUseCase;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.CreateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.user.UserDTOMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/bcn/api/user")
public class UserController {
  private final UserFacadeUseCase userFacadeUseCase;
  private final UserUseCase userUseCase;

  public UserController(UserFacadeUseCase userFacadeUseCase, UserUseCase userUseCase) {
    this.userFacadeUseCase = userFacadeUseCase;
    this.userUseCase = userUseCase;
  }


  @PostMapping
  public Mono<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
    User user = UserDTOMapper.mapToModel(createUserDto);
    return userFacadeUseCase.createUserAndAssignTheirRoles(user);
  }

  @GetMapping("/{userId}")
  public Mono<UserDTO> getUserById(@PathVariable String userId) {
    return userUseCase.getUserById(userId)
      .map(UserDTOMapper::mapToDto);
  }
}



