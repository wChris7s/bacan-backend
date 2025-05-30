package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.AddressDataBasePort;
import com.bacan.app.domain.address.Address;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.AddressEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.AddressRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class AddressPostgresAdapter implements AddressDataBasePort {
  private final AddressRepository addressRepository;

  public AddressPostgresAdapter(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public Mono<Address> createAddress(Address address) {
    AddressEntity addressEntity = AddressEntityMapper.mapToEntity(address);
    return this.addressRepository.save(addressEntity)
        .map(AddressEntityMapper::mapToModel);

  }

  @Override
  public Flux<Address> findAllAdresses() {
    return null;
  }


}
