package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.location.district.District;
import reactor.core.publisher.Flux;

public interface DistrictUseCase {
  Flux<District> findDistrictsByProvinceAndStateId(String provinceId, String stateId);
}
