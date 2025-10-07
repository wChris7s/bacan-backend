package com.bacan.app.application.services;

import com.bacan.app.application.port.in.DistrictUseCase;
import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.models.location.district.District;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.country.CountryDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.FullDistrictDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.province.FullProvinceDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.state.FullStateDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DistrictService implements DistrictUseCase {

  private final LocationDatabasePort locationDatabasePort;

  public DistrictService(LocationDatabasePort locationDatabasePort) {
    this.locationDatabasePort = locationDatabasePort;
  }

  @Override
  public Flux<District> getDistrictsByProvinceId(String provinceId) {
    return locationDatabasePort.findAllDistrictsByProvinceAndStateId(provinceId);
  }

  @Override
  public Mono<District> getDistrictById(String districtId) {
    return locationDatabasePort.findDistrictById(districtId);
  }

  @Override
  public Mono<FullDistrictDTO> getFullDistrictById(String districtId) {
    return locationDatabasePort.findDistrictById(districtId)
      .flatMap(district -> locationDatabasePort.findProvinceById(district.provinceId())
        .flatMap(province -> locationDatabasePort.findStateById(province.stateId())
          .flatMap(state -> locationDatabasePort.findCountryById(state.countryId())
            .map(country ->
              FullDistrictDTO.builder()
                .id(district.id())
                .name(district.name())
                .province(FullProvinceDTO.builder()
                  .id(province.id())
                  .name(province.name())
                  .state(FullStateDTO.builder()
                    .id(state.id())
                    .name(state.name())
                    .country(CountryDTO.builder()
                      .id(country.id())
                      .name(country.name())
                      .langCode(country.langCode())
                      .phoneCode(country.phoneCode())
                      .build())
                    .build())
                  .build())
                .build()
            ))));
  }
}
