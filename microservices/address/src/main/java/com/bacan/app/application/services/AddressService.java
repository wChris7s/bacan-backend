package com.bacan.app.application.services;

import com.bacan.app.application.port.in.AddressUseCase;
import com.bacan.app.application.port.out.persistence.AddressDataBasePort;
import com.bacan.app.domain.model.address.Address;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class AddressService implements AddressUseCase {
  private final AddressDataBasePort addressDataBasePort;

  public AddressService(AddressDataBasePort addressDataBasePort) {
    this.addressDataBasePort = addressDataBasePort;
  }

  @Override
  public Mono<Address> createAddress(Address address) {
    return this.addressDataBasePort.createAddress(address
        .withCreatedAt(LocalDateTime.now())
        .withUpdatedAt(LocalDateTime.now())
    );
  }

  @Override
  public Flux<Address> getAllAddresses() {
    return this.addressDataBasePort.findAllAdresses();
  }
}
