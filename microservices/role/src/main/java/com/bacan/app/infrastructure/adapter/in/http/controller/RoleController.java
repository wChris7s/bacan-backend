package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.RoleUseCase;
import com.bacan.app.domain.model.role.Role;
import com.bacan.app.infrastructure.adapter.in.http.controller.mapper.role.RoleDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.dto.role.CreateRoleDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.role.RoleDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(path = "/bcn/api/role")
public class RoleController {
  private final RoleUseCase roleUseCase;

  public RoleController(RoleUseCase roleUseCase) {
    this.roleUseCase = roleUseCase;
  }

  @GetMapping
  public Flux<RoleDTO> getAllRoles() {
    return this.roleUseCase.getAllRoles()
      .map(RoleDTOMapper::mapToDto);
  }

  @GetMapping("/public")
  public Flux<RoleDTO> getAllPublicRoles() {
    return this.roleUseCase.getAllPublicRoles()
      .map(RoleDTOMapper::mapToDto);
  }

  @GetMapping(path = "/{roleId}/validate")
  public Mono<Void> validateRoles(@PathVariable Long roleId) {
    return this.roleUseCase.validateRole(roleId);
  }

  @PostMapping
  public Mono<Void> createRole(@RequestBody CreateRoleDTO createRoleDto) {
    Role role = RoleDTOMapper.mapToModel(createRoleDto);
    return this.roleUseCase.createRole(role).then();
  }
}
