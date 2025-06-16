package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.location.country.Country;
import com.bacan.app.domain.models.location.district.District;
import com.bacan.app.domain.models.location.province.Province;
import com.bacan.app.domain.models.location.state.State;
import reactor.core.publisher.Flux;

public interface LocationDatabasePort {
  Flux<State> findAllStatesByCountryId(Long countryId);

  Flux<District> findAllDistrictsByProvinceAndStateId(String provinceId, String stateId);

  Flux<Country> findAllCountries();

  Flux<Province> findAllProvincesByStateId(String stateId);
}
