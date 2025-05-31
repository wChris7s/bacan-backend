package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.model.address.Address;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressDataBasePort {
  Mono<Address> createAddress(Address address);
  Flux<Address> findAllAdresses();
}
