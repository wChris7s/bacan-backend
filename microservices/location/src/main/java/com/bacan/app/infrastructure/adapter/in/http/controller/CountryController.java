package com.bacan.app.infrastructure.adapter.in.http.controller;

import com.bacan.app.application.port.in.CountryUseCase;
import com.bacan.app.infrastructure.adapter.in.http.controller.mapper.location.country.CountryDTOMapper;
import com.bacan.app.infrastructure.adapter.in.http.dto.location.country.CountryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/bcn/api/location/country")
public class CountryController {

  private final CountryUseCase countryUseCase;

  public CountryController(CountryUseCase countryUseCase) {
    this.countryUseCase = countryUseCase;
  }

  @GetMapping()
  public Mono<ResponseEntity<Flux<CountryDTO>>> getAllCountriesByName() {
    return Mono.just(ResponseEntity.ok(countryUseCase
      .findCountries()
      .map(CountryDTOMapper::mapToDto))
    );
  }
}
