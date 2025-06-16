package com.bacan.app.infrastructure.config;

import com.bacan.app.application.port.in.CountryUseCase;
import com.bacan.app.application.port.in.DistrictUseCase;
import com.bacan.app.application.port.in.ProvinceUseCase;
import com.bacan.app.application.port.in.StateUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.application.services.CountryService;
import com.bacan.app.application.services.DistrictService;
import com.bacan.app.application.services.ProvinceService;
import com.bacan.app.application.services.StateService;
import com.bacan.app.infrastructure.adapter.out.persistence.LocationPostgresAdapter;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.CountryRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.DistrictRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProvinceRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfig {
  @Bean
  public LocationDatabasePort locationDatabasePort(
    CountryRepository countryRepository,
    DistrictRepository districtRepository,
    ProvinceRepository provinceRepository,
    StateRepository stateRepository) {
    return LocationPostgresAdapter.builder()
      .countryRepository(countryRepository)
      .districtRepository(districtRepository)
      .provinceRepository(provinceRepository)
      .stateRepository(stateRepository)
      .build();
  }

  @Bean
  public CountryUseCase countryUseCase(LocationDatabasePort locationDatabasePort) {
    return new CountryService(locationDatabasePort);
  }

  @Bean
  public DistrictUseCase districtUseCase(LocationDatabasePort locationDatabasePort) {
    return new DistrictService(locationDatabasePort);
  }

  @Bean
  public ProvinceUseCase provinceUseCase(LocationDatabasePort locationDatabasePort) {
    return new ProvinceService(locationDatabasePort);
  }

  @Bean
  public StateUseCase stateUseCase(LocationDatabasePort locationDatabasePort) {
    return new StateService(locationDatabasePort);
  }
}
