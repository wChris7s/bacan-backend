package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.facade.UserFacade;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.domain.models.user.User;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.CreateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UpdateUserDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.user.UserDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.user.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/bcn/api/user")
@RequiredArgsConstructor
public class UserController {

  static final UserDTOMapper dtoMapper = Mappers.getMapper(UserDTOMapper.class);

  private final UserFacade userFacade;

  private final UserUseCase userUseCase;

  @PostMapping
  public Mono<Void> createUser(@RequestBody CreateUserDTO dto) {
    User user = dtoMapper.map(dto);
    return userFacade.createUser(user)
      .then();
  }

  @PostMapping("/{documentId}")
  public Mono<Void> updateUser(@PathVariable String documentId, @RequestBody UpdateUserDTO dto) {
    User user = dtoMapper.map(dto);
    return userFacade.updateUser(documentId, user)
      .then();
  }

  @GetMapping()
  public Flux<UserDTO> getAllUsers() {
    return userUseCase.getAll()
      .map(dtoMapper::map);
  }

  @GetMapping("/{documentId}")
  public Mono<UserDTO> getUserByDocumentId(@PathVariable String documentId) {
    return userUseCase.getUserByDocumentIdOrThrow(documentId)
      .map(dtoMapper::map);
  }
}



