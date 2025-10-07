package com.bacan.app.application.services;

import com.bacan.app.application.port.in.AddressUseCase;
import com.bacan.app.application.port.out.persistence.AddressDatabasePort;
import com.bacan.app.domain.models.address.Address;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressUseCase {

  private final Logger logger = LoggerFactory.getLogger(AddressService.class);

  private final AddressDatabasePort addressDatabasePort;

  @Override
  @Transactional
  public Mono<Address> createUserAddress(Address address) {
    address.setEnabled(true);
    return this.addressDatabasePort.createAddress(address)
      .doOnSuccess(savedAddress -> logger.info("Address with Id {} was created.", savedAddress.getId()))
      .doOnError(e -> logger.error("An error occurred while trying to create address: {}", e.getMessage()));
  }

  @Override
  @Transactional
  public Mono<Address> updateUserAddress(Long addressId, Address address) {
    return this.addressDatabasePort.getAddressById(addressId)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Address not found"))))
      .map(savedAddress -> {
        savedAddress.setDistrict(address.getDistrict());
        savedAddress.setStreet(address.getStreet());
        savedAddress.setPostalCode(address.getPostalCode());
        savedAddress.setNumber(address.getNumber());
        savedAddress.setReference(address.getReference());
        return savedAddress;
      })
      .flatMap(addressDatabasePort::updateAddress)
      .doOnSuccess(savedAddress -> logger.info("Address with Id {} was updated.", savedAddress.getId()))
      .doOnError(e -> logger.error("An error occurred while trying to update address: {}", e.getMessage()));
  }

  @Override
  public Mono<Address> getUserByIdOrThrow(Long addressId) {
    return addressDatabasePort.getAddressById(addressId)
      .switchIfEmpty(Mono.defer(() -> {
        logger.error("Address with Id {} not found.", addressId);
        throw new RuntimeException("Address not found");
      }));
  }
}
