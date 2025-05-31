package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.UserFacadeUseCase;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.domain.model.user.User;
import com.bacan.app.infrastructure.adapter.in.http.controller.mapper.user.UserDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.CreateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/bcn/api/ms-user")
public class UserController {
  private final UserFacadeUseCase userFacadeUseCase;
  private final UserUseCase userUseCase;

  public UserController(UserFacadeUseCase userFacadeUseCase, UserUseCase userUseCase) {
    this.userFacadeUseCase = userFacadeUseCase;
    this.userUseCase = userUseCase;
  }

  @GetMapping
  public Flux<UserDTO> getAllUsers() {
    return this.userUseCase.getAllUser()
        .map(UserDTOMapper::mapToDto);
  }

  @PostMapping
  public Mono<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
    User user = UserDTOMapper.mapToModel(createUserDto);
    return userFacadeUseCase.createUserAndAssignTheirRoles(user);
  }
}



