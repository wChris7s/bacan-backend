package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.address.Address;
import reactor.core.publisher.Mono;

public interface AddressUseCase {
  Mono<Void> createUserAddress(Address address);
}
