package com.bacan.app.application.services;

import com.bacan.app.application.port.in.DistrictUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.models.location.district.District;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DistrictService implements DistrictUseCase {

  private final LocationDatabasePort locationDatabasePort;

  public DistrictService(LocationDatabasePort locationDatabasePort) {
    this.locationDatabasePort = locationDatabasePort;
  }

  @Override
  public Flux<District> findDistrictsByProvinceId(String provinceId) {
    return locationDatabasePort.findAllDistrictsByProvinceAndStateId(provinceId);
  }

  @Override
  public Mono<District> findDistrictById(String districtId) {
    return locationDatabasePort.findDistrictById(districtId);
  }
}
