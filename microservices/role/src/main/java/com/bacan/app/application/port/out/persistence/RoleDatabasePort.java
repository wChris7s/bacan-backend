package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleDatabasePort {
    Mono<Role> createRole(Role role);

    Flux<Role> findAllRoles();
}






