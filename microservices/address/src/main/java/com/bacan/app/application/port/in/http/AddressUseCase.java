package com.bacan.app.application.port.in.http;

import com.bacan.app.domain.address.Address;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressUseCase {
  Mono<Address> createAddress(Address address);
  Flux<Address> getAllAddresses();
}
