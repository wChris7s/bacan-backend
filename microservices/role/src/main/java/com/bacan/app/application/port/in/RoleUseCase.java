package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleUseCase {
    Mono<Role> createRole(Role role);

    Flux<Role> getAllRoles();

    Mono<Void> validateRoles(List<Long> roleIds);
}
