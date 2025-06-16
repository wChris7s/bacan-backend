package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.location.province.Province;
import reactor.core.publisher.Flux;

public interface ProvinceUseCase {
  Flux<Province> findProvincesByStateId(String stateId);
}
