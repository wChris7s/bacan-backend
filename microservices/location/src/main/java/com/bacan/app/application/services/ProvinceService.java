package com.bacan.app.application.services;

import com.bacan.app.application.port.in.ProvinceUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.models.location.province.Province;
import reactor.core.publisher.Flux;

public class ProvinceService implements ProvinceUseCase {

  private final LocationDatabasePort locationDatabasePort;

  public ProvinceService(LocationDatabasePort locationDatabasePort) {
    this.locationDatabasePort = locationDatabasePort;
  }

  @Override
  public Flux<Province> findProvincesByStateId(String stateId) {
    return locationDatabasePort.findAllProvincesByStateId(stateId);
  }
}
