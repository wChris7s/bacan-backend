package com.bacan.app.application.services;

import com.bacan.app.application.port.in.AddressUseCase;
import com.bacan.app.application.port.out.persistence.AddressDatabasePort;
import com.bacan.app.domain.model.address.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class AddressService implements AddressUseCase {
  private final Logger logger = LoggerFactory.getLogger(AddressService.class);
  private final AddressDatabasePort addressDatabasePort;

  public AddressService(AddressDatabasePort addressDatabasePort) {
    this.addressDatabasePort = addressDatabasePort;
  }

  @Override
  public Mono<Void> createUserAddress(Address address) {
    LocalDateTime actualDateTime = LocalDateTime.now(ZoneId.of("America/Lima"));
    return this.addressDatabasePort.createAddress(address
        .withCreatedAt(actualDateTime)
        .withUpdatedAt(actualDateTime))
      .doOnSuccess(savedAddress -> logger.info("Address of user with ID {} was saved.", savedAddress.getUserId()))
      .doOnError(e -> logger.error("Error details: {}", e.getMessage()))
      .then();
  }
}
