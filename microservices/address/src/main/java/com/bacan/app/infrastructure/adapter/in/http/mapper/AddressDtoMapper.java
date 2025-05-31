package com.bacan.app.infrastructure.adapter.in.http.mapper;

import com.bacan.app.domain.model.address.Address;
import com.bacan.app.infrastructure.adapter.in.http.dto.AddressDto;

public class AddressDtoMapper {
  public static AddressDto mapToDto(Address address) {
    return AddressDto.builder()
        .id(address.getId())
        .street(address.getStreet())
        .postalCode(address.getPostalCode())
        .number(address.getNumber())
        .reference(address.getReference())
        .createdAt(address.getCreatedAt())
        .updatedAt(address.getUpdatedAt())
        .build();
  }


}
