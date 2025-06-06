package com.bacan.app.domain.model.user;

import com.bacan.app.domain.model.address.Address;
import com.bacan.app.domain.model.role.Role;
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
  @With String profilePhoto,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With boolean enabled,
  @With LocalDateTime passwordModifiedDate,
  @With Role role,
  @With Address address,
  Long phoneCountryId) {
}
