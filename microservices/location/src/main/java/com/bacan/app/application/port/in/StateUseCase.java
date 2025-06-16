package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.location.state.State;
import reactor.core.publisher.Flux;

public interface StateUseCase {
  Flux<State> findStatesByCountryId(Long countryId);
}
