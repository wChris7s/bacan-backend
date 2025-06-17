package com.bacan.app.infrastructure.config;

import com.bacan.app.application.facades.UserFacade;
import com.bacan.app.application.port.in.AddressUseCase;
import com.bacan.app.application.port.in.UserFacadeUseCase;
import com.bacan.app.application.port.in.UserRoleUseCase;
import com.bacan.app.application.port.in.UserUseCase;
import com.bacan.app.application.port.out.http.FileManager;
import com.bacan.app.application.port.out.http.RoleMicroservice;
import com.bacan.app.application.port.out.persistence.AddressDatabasePort;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.application.port.out.persistence.UserRoleDatabasePort;
import com.bacan.app.application.services.AddressService;
import com.bacan.app.application.services.UserRoleService;
import com.bacan.app.application.services.UserService;
import com.bacan.app.infrastructure.adapter.out.http.RoleMicroserviceClientAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.AddressPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.UserPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.UserRolePostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.AddressRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.UserRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.UserRoleRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserConfig {
  @Bean
  public UserDatabasePort userDatabasePort(UserRepository userRepository) {
    return new UserPostgresAdapter(userRepository);
  }

  @Bean
  public UserUseCase userUseCase(UserDatabasePort userDatabasePort) {
    return new UserService(userDatabasePort);
  }

  @Bean
  public UserRoleRepository userRoleRepository(ConnectionFactory connectionFactory) {
    return new UserRoleRepository(DatabaseClient.create(connectionFactory));
  }

  @Bean
  public UserRoleDatabasePort userRoleDatabasePort(UserRoleRepository userRoleRepository) {
    return new UserRolePostgresAdapter(userRoleRepository);
  }

  @Bean
  public UserRoleUseCase userRoleUseCase(UserRoleDatabasePort userRoleDatabasePort) {
    return new UserRoleService(userRoleDatabasePort);
  }

  @Bean
  public AddressDatabasePort addressDatabasePort(AddressRepository addressRepository) {
    return new AddressPostgresAdapter(addressRepository);
  }

  @Bean
  public AddressUseCase addressUseCase(AddressDatabasePort addressDatabasePort) {
    return new AddressService(addressDatabasePort);
  }

  @Bean
  public RoleMicroservice rolePort(@Value("${application.microservice.ms-role}") String baseUrl) {
    return new RoleMicroserviceClientAdapter(WebClient.create(baseUrl + "/bcn/api/role"));
  }

  @Bean
  public UserFacadeUseCase userFacadeUseCase(
    UserUseCase userUseCase,
    UserRoleUseCase userRoleUseCase,
    RoleMicroservice roleMicroservice,
    AddressUseCase addressUseCase,
    FileManager fileManager) {
    return UserFacade.builder()
      .userUseCase(userUseCase)
      .userRoleUseCase(userRoleUseCase)
      .roleMicroservice(roleMicroservice)
      .addressUseCase(addressUseCase)
      .fileManager(fileManager)
      .build();
  }
}
