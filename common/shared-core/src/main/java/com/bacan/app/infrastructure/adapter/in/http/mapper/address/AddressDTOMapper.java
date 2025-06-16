package com.bacan.app.infrastructure.adapter.in.http.mapper.address;

import com.bacan.app.domain.models.address.Address;
import com.bacan.app.infrastructure.adapter.in.http.dto.address.CreateAddressDTO;
import com.bacan.app.infrastructure.adapter.in.http.dto.address.UpdateAddressDTO;

public class AddressDTOMapper {
  public static Address mapToModel(String userId, CreateAddressDTO address) {
    return Address.builder()
      .userId(userId)
      .countryId(address.getCountryId())
      .stateId(address.getStateId())
      .provinceId(address.getProvinceId())
      .districtId(address.getDistrictId())
      .postalCode(address.getPostalCode())
      .street(address.getStreet())
      .number(address.getNumber())
      .reference(address.getReference())
      .build();
  }

  public static Address mapToModel(String userId, UpdateAddressDTO address) {
    return Address.builder()
      .id(address.getId())
      .userId(userId)
      .countryId(address.getCountryId())
      .stateId(address.getStateId())
      .provinceId(address.getProvinceId())
      .districtId(address.getDistrictId())
      .postalCode(address.getPostalCode())
      .street(address.getStreet())
      .number(address.getNumber())
      .reference(address.getReference())
      .build();
  }
}
