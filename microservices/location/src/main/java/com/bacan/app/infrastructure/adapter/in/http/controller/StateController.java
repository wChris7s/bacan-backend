package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.StateUseCase;
import com.bacan.app.infrastructure.adapter.in.http.controller.mapper.location.state.StateDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.state.StateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/ms-location/state")
public class StateController {
  private final StateUseCase stateUseCase;

  public StateController(StateUseCase stateUseCase) {
    this.stateUseCase = stateUseCase;
  }

  @GetMapping("country/{countryId}")
  public Mono<ResponseEntity<Flux<StateDTO>>> getAllDistricts(
    @PathVariable Long countryId) {
    return Mono.just(ResponseEntity.ok(stateUseCase
      .findStatesByCountryId(countryId)
      .map(StateDTOMapper::mapToDto)));
  }
}
