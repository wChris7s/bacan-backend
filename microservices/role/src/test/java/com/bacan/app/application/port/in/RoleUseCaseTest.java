package com.bacan.app.application.port.in;

import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.application.services.RoleService;
import com.bacan.app.domain.model.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

  @Mock
  RoleDatabasePort roleDatabasePort;

  @InjectMocks
  RoleService roleService;

  private LocalDateTime now;

  @BeforeEach
  void beforeAll() {
    now = LocalDateTime.now();
  }

  @Test
  void createRole() {
    Role inputRole = Role.builder()
        .name("ADMIN")
        .build();

    Role outputRole = Role.builder()
        .id(1L)
        .name("ADMIN")
        .enabled(true)
        .createdAt(now)
        .updatedAt(now)
        .build();

    when(roleDatabasePort.createRole(any())).thenReturn(Mono.just(outputRole));
    Mono<Role> result = roleService.createRole(inputRole);

    StepVerifier.create(result)
        .expectNextMatches(role -> role.getId().equals(outputRole.getId()) &&
            role.getName().equals(outputRole.getName()) &&
            role.getCreatedAt().equals(outputRole.getCreatedAt()) &&
            role.getUpdatedAt().equals(outputRole.getUpdatedAt()) &&
            role.isEnabled() == outputRole.isEnabled()
        ).verifyComplete();

    verify(roleDatabasePort, times(1)).createRole(any());
  }

  @Test
  void getAllRoles() {
  }
}