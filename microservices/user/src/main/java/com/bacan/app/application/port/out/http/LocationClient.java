package com.bacan.app.application.port.out.http;

import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.FullDistrictDTO;
import reactor.core.publisher.Mono;

public interface LocationClient {
  Mono<FullDistrictDTO> getFullDistrictById(String id);
}
