package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.in.http.AddressUseCase;
import com.bacan.app.application.port.out.persistence.AddressDataBasePort;
import com.bacan.app.application.services.AddressService;
import com.bacan.app.infrastructure.adapter.out.persistence.AddressPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.AddressRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {
  @Bean
  public AddressDataBasePort addressDataBasePort(AddressRepository addressRepository) {
    return new AddressPostgresAdapter(addressRepository);
  }

  @Bean
  public AddressUseCase addressUseCase(AddressDataBasePort addressDataBasePort) {
    return new AddressService(addressDataBasePort);
  }
}
