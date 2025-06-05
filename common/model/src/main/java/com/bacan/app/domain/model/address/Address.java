package com.bacan.app.domain.model.address;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Builder
public class Address {
  Long id;
  String userId;
  Long countryId;
  String stateId;
  String provinceId;
  String districtId;
  String street;
  String postalCode;
  String number;
  String reference;
  @With
  LocalDateTime createdAt;
  @With
  LocalDateTime updatedAt;
}
