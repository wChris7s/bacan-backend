package com.bacan.app.application.port.out.persistence;

import com.bacan.app.domain.model.address.Address;
import reactor.core.publisher.Mono;

public interface AddressDatabasePort {
  Mono<Address> createAddress(Address address);
}
