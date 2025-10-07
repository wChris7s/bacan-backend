package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.location.district.District;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.FullDistrictDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DistrictUseCase {
  Flux<District> getDistrictsByProvinceId(String provinceId);

  Mono<District> getDistrictById(String districtId);

  Mono<FullDistrictDTO> getFullDistrictById(String districtId);
}
