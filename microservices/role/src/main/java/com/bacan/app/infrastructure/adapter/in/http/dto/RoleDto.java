package com.bacan.app.infrastructure.adapter.in.http.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class RoleDto {
  Long id;
  String name;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  boolean enabled;
}
