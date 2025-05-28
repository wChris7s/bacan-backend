package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.http.UserUseCase;
import com.bacan.app.domain.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.CreateUserDto;
import com.bacan.app.infrastructure.adapter.in.http.dto.UserDto;
import com.bacan.app.infrastructure.adapter.in.http.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value="/api/user")
public class UserController {
  private final UserUseCase userUseCase;

  public UserController(UserUseCase userUseCase) {
    this.userUseCase = userUseCase;
  }

  @GetMapping
  public Flux<UserDto> getAllUsers() {
    return this.userUseCase.getAllUser()
        .map(UserDtoMapper::mapToDto);
  }

  @PostMapping
  public Mono<Void> createUser(@RequestBody CreateUserDto createUserDto){
    User user = UserDtoMapper.mapToModel(createUserDto);
    return this.userUseCase.createUser(user).then();

  }
}



