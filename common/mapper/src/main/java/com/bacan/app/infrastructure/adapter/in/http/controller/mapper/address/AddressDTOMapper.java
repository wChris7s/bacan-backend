package com.bacan.app.infrastructure.adapter.in.http.controller.mapper.address;

import com.bacan.app.domain.model.address.Address;
import com.bacan.app.infrastructure.adapter.in.http.dto.address.CreateAddressDTO;

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
}
