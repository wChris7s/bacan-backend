package com.bacan.app.domain.models.store;

import com.bacan.app.domain.models.user.User;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record Store(
  String id,
  String name,
  LocalTime open,
  LocalTime close,
  String story,
  String logo,
  String background,
  String userId,
  @With LocalDateTime createdAt,
  @With LocalDateTime updatedAt,
  @With boolean enabled,

  @With User user) {
}

