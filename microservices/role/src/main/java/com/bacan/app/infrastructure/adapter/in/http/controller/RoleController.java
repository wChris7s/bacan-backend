package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.http.RoleUseCase;
import com.bacan.app.domain.role.Role;
import com.bacan.app.infrastructure.adapter.in.http.dto.CreateRoleDto;
import com.bacan.app.infrastructure.adapter.in.http.dto.RoleDto;
import com.bacan.app.infrastructure.adapter.in.http.mapper.RoleDtoMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController()
@RequestMapping(path = "/api/role")
public class RoleController {
  private final RoleUseCase roleUseCase;

  public RoleController(RoleUseCase roleUseCase) {
    this.roleUseCase = roleUseCase;
  }

  @GetMapping
  public Flux<RoleDto> getAllRoles() {
    return this.roleUseCase.getAllRoles()
        .map(RoleDtoMapper::mapToDto);
  }

  @GetMapping(path = "/validate")
  public Mono<Void> validateRoles(@RequestParam List<Long> roleIds) {
    return this.roleUseCase.validateRoles(roleIds);
  }

  @PostMapping
  public Mono<Void> createRole(@RequestBody CreateRoleDto createRoleDto) {
    Role role = RoleDtoMapper.mapToModel(createRoleDto);
    return this.roleUseCase.createRole(role).then();
  }
}
