package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.LocationDatabasePort;
import com.bacan.app.domain.models.location.country.Country;
import com.bacan.app.domain.models.location.district.District;
import com.bacan.app.domain.models.location.province.Province;
import com.bacan.app.domain.models.location.state.State;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.CountryEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.DistrictEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.ProvinceEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.StateEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.CountryRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.DistrictRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.ProvinceRepository;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.StateRepository;
import lombok.Builder;
import reactor.core.publisher.Flux;

@Builder
public class LocationPostgresAdapter implements LocationDatabasePort {

  private final CountryRepository countryRepository;

  private final DistrictRepository districtRepository;

  private final ProvinceRepository provinceRepository;

  private final StateRepository stateRepository;

  @Override
  public Flux<State> findAllStatesByCountryId(Long countryId) {
    return stateRepository.findAllByCountryId(countryId)
      .map(StateEntityMapper::mapToModel);
  }

  @Override
  public Flux<District> findAllDistrictsByProvinceAndStateId(String provinceId, String stateId) {
    return districtRepository.findAllByProvinceIdAndStateId(provinceId, stateId)
      .map(DistrictEntityMapper::mapToModel);
  }

  @Override
  public Flux<Country> findAllCountries() {
    return countryRepository.findAll()
      .map(CountryEntityMapper::mapToModel);
  }

  @Override
  public Flux<Province> findAllProvincesByStateId(String stateId) {
    return provinceRepository.findAllByStateId(stateId)
      .map(ProvinceEntityMapper::mapToModel);
  }
}
