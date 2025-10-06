package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.location.district.District;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DistrictUseCase {
  Flux<District> findDistrictsByProvinceId(String provinceId);

  Mono<District> findDistrictById(String districtId);
}
