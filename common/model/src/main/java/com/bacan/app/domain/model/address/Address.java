package com.bacan.app.domain.model.address;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class Address {
  Long id;
  String userId;
  Long countryId;
  Long stateId;
  Long provinceId;
  Long districtId;
  String street;
  String postalCode;
  String number;
  String reference;
  @With
  LocalDateTime createdAt;
  @With
  LocalDateTime updatedAt;
}
