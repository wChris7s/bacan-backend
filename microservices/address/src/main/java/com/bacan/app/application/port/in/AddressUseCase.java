package com.bacan.app.application.port.in;

import com.bacan.app.domain.model.address.Address;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressUseCase {
  Mono<Address> createAddress(Address address);
  Flux<Address> getAllAddresses();
}
