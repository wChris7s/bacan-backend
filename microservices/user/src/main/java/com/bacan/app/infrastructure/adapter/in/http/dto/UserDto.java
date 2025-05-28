package com.bacan.app.infrastructure.adapter.in.http.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
@Value
@Builder
@Jacksonized

public class UserDto {
  Long documentId;
  String name;
  String lastname;
  LocalDateTime birthDate;
  String phone;
  String email;
  String profilePhoto;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  boolean enabled;

}
