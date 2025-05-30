package com.bacan.app.infrastructure.adapter.in.http.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class AddressDto {
  Long id;
  String street;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  String postalCode;
  String number;
  String reference;
}
        