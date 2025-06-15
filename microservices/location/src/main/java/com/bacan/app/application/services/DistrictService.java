package com.bacan.app.application.services;

import com.bacan.app.application.port.in.DistrictUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.model.location.district.District;
import reactor.core.publisher.Flux;

public class DistrictService implements DistrictUseCase {

  private final LocationDatabasePort locationDatabasePort;

  public DistrictService(LocationDatabasePort locationDatabasePort) {
    this.locationDatabasePort = locationDatabasePort;
  }

  @Override
  public Flux<District> findDistrictsByProvinceAndStateId(String provinceId, String stateId) {
    return locationDatabasePort.findAllDistrictsByProvinceAndStateId(provinceId, stateId);
  }
}
