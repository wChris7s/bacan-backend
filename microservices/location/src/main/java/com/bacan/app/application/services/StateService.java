package com.bacan.app.application.services;

import com.bacan.app.application.port.in.StateUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.model.location.state.State;
import reactor.core.publisher.Flux;

public class StateService implements StateUseCase {

  private final LocationDatabasePort locationDatabasePort;

  public StateService(LocationDatabasePort locationDatabasePort) {
    this.locationDatabasePort = locationDatabasePort;
  }

  @Override
  public Flux<State> findStatesByCountryId(Long countryId) {
    return locationDatabasePort.findAllStatesByCountryId(countryId);
  }
}
