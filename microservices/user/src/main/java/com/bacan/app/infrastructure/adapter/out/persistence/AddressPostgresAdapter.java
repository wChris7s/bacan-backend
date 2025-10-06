package com.bacan.app.infrastructure.adapter.out.persistence;

import com.bacan.app.application.port.out.persistence.AddressDatabasePort;
import com.bacan.app.domain.models.address.Address;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.AddressEntity;
import com.bacan.app.infrastructure.adapter.out.persistence.mapper.AddressEntityMapper;
import com.bacan.app.infrastructure.adapter.out.persistence.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AddressPostgresAdapter implements AddressDatabasePort {

  private final AddressRepository addressRepository;

  static final AddressEntityMapper addressEntityMapper = Mappers.getMapper(AddressEntityMapper.class);

  @Override
  public Mono<Address> createAddress(Address address) {
    AddressEntity entity = addressEntityMapper.mapToEntity(address);
    return addressRepository.save(entity)
      .map(addressEntityMapper::mapToModel);
  }

  @Override
  public Mono<Address> updateAddress(Address address) {
    AddressEntity entity = addressEntityMapper.mapToEntity(address);
    return addressRepository.save(entity)
      .map(addressEntityMapper::mapToModel);
  }

  @Override
  public Mono<Address> getAddressById(Long id) {
    return addressRepository.findById(id)
      .map(addressEntityMapper::mapToModel);
  }
}
