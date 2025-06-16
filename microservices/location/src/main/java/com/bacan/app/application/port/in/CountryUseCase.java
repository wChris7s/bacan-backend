package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.location.country.Country;
import reactor.core.publisher.Flux;

public interface CountryUseCase {
  Flux<Country> findCountries();
}
