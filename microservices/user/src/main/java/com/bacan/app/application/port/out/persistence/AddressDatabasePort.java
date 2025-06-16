package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.models.address.Address;
import reactor.core.publisher.Mono;

public interface AddressDatabasePort {
  Mono<Address> createAddress(Address address);

  Mono<Address> updateAddress(Address address);
}
