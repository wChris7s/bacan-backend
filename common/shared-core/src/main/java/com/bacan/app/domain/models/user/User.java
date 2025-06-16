package com.bacan.app.domain.models.user;

import com.bacan.app.domain.models.address.Address;
import com.bacan.app.domain.models.role.Role;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record User(
  String documentId,
  String name,
  String lastname,
  LocalDate birthDate,
  String phone,
  String email,
  String password,
  @With String photo,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With boolean enabled,
  @With LocalDateTime passwordModifiedDate,
  @With Role role,
  @With Address address,
  Long phoneCountryId) {
}
