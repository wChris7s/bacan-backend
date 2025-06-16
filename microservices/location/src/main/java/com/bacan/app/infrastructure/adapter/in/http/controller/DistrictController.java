package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.DistrictUseCase;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.district.DistrictDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.location.district.DistrictDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/location/district")
public class DistrictController {

  private final DistrictUseCase districtUseCase;

  public DistrictController(DistrictUseCase districtUseCase) {
    this.districtUseCase = districtUseCase;
  }

  @GetMapping("province/{provinceId}/state/{stateId}")
  public Mono<ResponseEntity<Flux<DistrictDTO>>> getAllDistricts(
    @PathVariable String provinceId,
    @PathVariable String stateId) {
    return Mono.just(ResponseEntity.ok(districtUseCase
      .findDistrictsByProvinceAndStateId(provinceId, stateId)
      .map(DistrictDTOMapper::mapToDto))
    );
  }
}
