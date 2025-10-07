package com.bacan.app.infrastructure.adapter.in.http.controller.dto.store;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record StoreResponseDTO(
  String id,
  String name,
  LocalTime open,
  LocalTime close,
  String story,
  String logo,
  String background,
  String userId,
  boolean enabled,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {
}
