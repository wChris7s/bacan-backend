package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.in.http.RoleUseCase;
import com.bacan.app.application.port.out.persistence.RoleDatabasePort;
import com.bacan.app.application.services.RoleService;
import com.bacan.app.infrastructure.adapter.out.persistence.persistence.RolePostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.persistence.repository.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {
  @Bean
  public RoleDatabasePort roleDatabasePort(RoleRepository roleRepository) {
    return new RolePostgresAdapter(roleRepository);
  }

  @Bean
  public RoleUseCase roleUseCase(RoleDatabasePort roleDatabasePort) {
    return new RoleService(roleDatabasePort);
  }
}
