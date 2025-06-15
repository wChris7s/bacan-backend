package com.bacan.app.infrastructure.adapter.out.persistence.mapper;

import com.bacan.app.domain.model.address.Address;
import com.bacan.app.infrastructure.adapter.out.persistence.entity.AddressEntity;

public class AddressEntityMapper {
  public static AddressEntity mapToEntity(Address address) {
    return AddressEntity.builder()
      .id(address.getId())
      .userId(address.getUserId())
      .countryId(address.getCountryId())
      .stateId(address.getStateId())
      .provinceId(address.getProvinceId())
      .districtId(address.getDistrictId())
      .street(address.getStreet())
      .postalCode(address.getPostalCode())
      .number(address.getNumber())
      .reference(address.getReference())
      .createdAt(address.getCreatedAt())
      .updatedAt(address.getUpdatedAt())
      .build();

  }

  public static Address mapToModel(AddressEntity address) {
    return Address.builder()
      .id(address.getId())
      .userId(address.getUserId())
      .countryId(address.getCountryId())
      .stateId(address.getStateId())
      .provinceId(address.getProvinceId())
      .districtId(address.getDistrictId())
      .street(address.getStreet())
      .postalCode(address.getPostalCode())
      .number(address.getNumber())
      .reference(address.getReference())
      .createdAt(address.getCreatedAt())
      .updatedAt(address.getUpdatedAt())
      .build();
  }
}
