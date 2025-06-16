package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.ProvinceUseCase;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.province.ProvinceDTO;
import com.bacan.app.infrastructure.adapter.in.http.mapper.location.province.ProvinceDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/location/province")
public class ProvinceController {
  private final ProvinceUseCase provinceUseCase;

  public ProvinceController(ProvinceUseCase provinceUseCase) {
    this.provinceUseCase = provinceUseCase;
  }

  @GetMapping("state/{stateId}")
  public Mono<ResponseEntity<Flux<ProvinceDTO>>> getAllDistricts(
    @PathVariable String stateId) {
    return Mono.just(ResponseEntity.ok(provinceUseCase
      .findProvincesByStateId(stateId)
      .map(ProvinceDTOMapper::mapToDto))
    );
  }
}
