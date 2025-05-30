package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.in.http.UserUseCase;
import com.bacan.app.application.port.out.persistence.UserDatabasePort;
import com.bacan.app.application.services.UserService;
import com.bacan.app.infrastructure.adapter.out.persistence.UserPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
