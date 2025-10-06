package com.bacan.app.application.port.in;

import com.bacan.app.domain.models.address.Address;
import reactor.core.publisher.Mono;

public interface AddressUseCase {
  Mono<Address> createUserAddress(Address address);

  Mono<Address> updateUserAddress(Long addressId, Address address);

  Mono<Address> getUserByIdOrThrow(Long addressId);
}
