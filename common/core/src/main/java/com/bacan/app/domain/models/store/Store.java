package com.bacan.app.domain.models.store;

import com.bacan.app.domain.models.user.User;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record Store(
  Long id,
  String name,
  @With LocalTime open,
  @With LocalTime close,
  @With String story,
  String logoUrl,
  String backgroundUrl,
  @With String documentId,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With Boolean enabled,
  @With User user) {
}

