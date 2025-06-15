package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.AddressDatabasePort;
import com.bacan.app.domain.model.address.Address;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.AddressEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.AddressRepository;
import reactor.core.publisher.Mono;

public class AddressPostgresAdapter implements AddressDatabasePort {
  private final AddressRepository addressRepository;

  public AddressPostgresAdapter(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public Mono<Address> createAddress(Address address) {
    AddressEntity entity = AddressEntityMapper.mapToEntity(address).withNew(true);
    return addressRepository.save(entity)
      .map(AddressEntityMapper::mapToModel);
  }

  @Override
  public Mono<Address> updateAddress(Address address) {
    AddressEntity entity = AddressEntityMapper.mapToEntity(address).withNew(false);
    return addressRepository.save(entity)
      .map(AddressEntityMapper::mapToModel);
  }
}
