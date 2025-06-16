package com.bacan.app.application.services;

import com.bacan.app.application.port.in.CountryUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.models.location.country.Country;
import reactor.core.publisher.Flux;

public class CountryService implements CountryUseCase {

  private final LocationDatabasePort locationDatabasePort;

  public CountryService(LocationDatabasePort locationDatabasePort) {
    this.locationDatabasePort = locationDatabasePort;
  }

  @Override
  public Flux<Country> findCountries() {
    return locationDatabasePort.findAllCountries();
  }
}
