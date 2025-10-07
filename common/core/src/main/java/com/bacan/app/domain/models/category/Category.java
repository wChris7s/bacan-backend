package com.bacan.app.domain.models.category;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@Builder
public record Category(
  Long id,
  String name,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With Boolean enabled) {
}
